package application;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;

import core.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class SampleController implements Initializable, Cloneable {

	@FXML
	private Button AddButton, ResetButton, RunButton;// 버튼

	@FXML
	private ChoiceBox<String> cbox;// 프로세스 선택

	@FXML
	private TextField textField1, textField2, textField3, textField4;// 텍스트 필드

	@FXML
	private TableView<Process> tableView;// 테이블
	@FXML
	private TableColumn<Process, String> Pnames;
	@FXML
	private TableColumn<Process, Integer> ATColumn, BTColumn;

	@FXML
	private TableView<Process> tableView2;// 결과 테이블
	@FXML
	private TableColumn<Process, String> nameColumn1;
	@FXML
	private TableColumn<Process, Integer> ATColumn1, BTColumn1, WTColumn1, TTColumn1;
	@FXML
	private TableColumn<Process, Double> NTTColumn1;

	ObservableList<Process> observableList = FXCollections.observableArrayList();
	ObservableList<Process> obser = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cbox.getItems().removeAll(cbox.getItems());

		cbox.getItems().addAll("FCFS (First Come First Served)", "RR (Round Robin)", "SPN", "SRTN", "HRRN");// 셀 안에 있는
																											// 종류

		cbox.getSelectionModel().select("FCFS (First Come First Served)");// 셀 초기화
		tableView.setItems(observableList);

		Label placeholderLabel = new Label("");
		tableView.setPlaceholder(placeholderLabel);
		tableView2.setPlaceholder(placeholderLabel);

		// 셀 표시
		Pnames.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		ATColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalTimeProperty().asObject());
		BTColumn.setCellValueFactory(cellData -> cellData.getValue().burstTimeProperty().asObject());
		tableView.setItems(observableList);

		// 결과창 테이블
		nameColumn1.setCellValueFactory(cellData -> cellData.getValue().processNameProperty());
		ATColumn1.setCellValueFactory(cellData -> cellData.getValue().arrivalTimeProperty().asObject());
		BTColumn1.setCellValueFactory(cellData -> cellData.getValue().burstTimeProperty().asObject());
		WTColumn1.setCellValueFactory(cellData -> cellData.getValue().waitingTimeProperty().asObject());
		TTColumn1.setCellValueFactory(cellData -> cellData.getValue().turnaroundTimeProperty().asObject());
		NTTColumn1.setCellValueFactory(cellData -> cellData.getValue().NormalizedTTProperty().asObject());

		textField4.setDisable(true);
		cbox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals("RR (Round Robin)")) {
				textField4.setDisable(false);
			} else {
				textField4.setDisable(true);
			}
		});

		AddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 표 데이터 입력 마우스
		{
			@Override
			public void handle(MouseEvent arg0) {
				String str1, str2, str3;
				str1 = textField1.getText();// 프로세스 이름
				str2 = textField2.getText();// AT
				str3 = textField3.getText();// BT

				if (str1 == null || str1.trim().isEmpty()) {

					warning("empty", "Pname");
					return;
				} // 프로세스 이름이 입력되지 않을 경우

				if (str2 == null || str3 == null || str2.trim().isEmpty() || str3.trim().isEmpty()) {

					warning("empty", "AT or BT");
					textField2.clear();
					textField3.clear();
					return;
				} // AT 또는 BT가 비어있을 경우

				if ((!isInteger(str2) && !str2.equalsIgnoreCase("0"))
						|| (!isInteger(str3) && !str3.equalsIgnoreCase("0"))) // 타임쿼터스가 숫자가 아닐 경우 또는 오버플로우 언더플로우가 일어날 때
				{

					warning("number", "AT or BT");
					textField2.clear();
					textField3.clear();
					return;
				} // AT 또는 BT가 숫자가 아닐 경우;

				if (Integer.parseInt(str2) < 0 || Integer.parseInt(str3) < 0) {

					warning("mnumber", "AT or BT");
					textField2.clear();
					textField3.clear();
					return;
				}

				if (observableList.size() <= 15) {
					tableView.getItems().add(new Process(str1, Integer.parseInt(str2), Integer.parseInt(str3)));
				} else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Warning!!");
					alert.setHeaderText("full");
					alert.setContentText("table is full.");
				}

				//

				textField1.clear();
				textField2.clear();
				textField3.clear();
			}

		});

		AddButton.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() // 표 데이터 입력 엔터
		{
			@Override
			public void handle(KeyEvent arg0) {
				if (arg0.getCode() == KeyCode.ENTER) {
					String str1, str2, str3;
					str1 = textField1.getText();// 프로세스 이름
					str2 = textField2.getText();// AT
					str3 = textField3.getText();// BT

					if (str1 == null || str1.trim().isEmpty()) {

						warning("empty", "Pname");
						return;
					} // 프로세스 이름이 입력되지 않을 경우

					if (str2 == null || str3 == null || str2.trim().isEmpty() || str3.trim().isEmpty()) {

						warning("empty", "AT or BT");
						textField2.clear();
						textField3.clear();
						return;
					} // AT 또는 BT가 비어있을 경우

					if ((!isInteger(str2) && !str2.equalsIgnoreCase("0"))
							|| (!isInteger(str3) && !str3.equalsIgnoreCase("0"))) // 타임쿼터스가 숫자가 아닐 경우 또는 오버플로우 언더플로우가
																					// 일어날 때
					{

						warning("number", "AT or BT");
						textField2.clear();
						textField3.clear();
						return;
					} // AT 또는 BT가 숫자가 아닐 경우;

					if (Integer.parseInt(str2) < 0 || Integer.parseInt(str3) < 0) {

						warning("mnumber", "AT or BT");
						textField2.clear();
						textField3.clear();
						return;
					}

					if (observableList.size() <= 15) {
						tableView.getItems().add(new Process(str1, Integer.parseInt(str2), Integer.parseInt(str3)));
					} else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Warning!!");
						alert.setHeaderText("full");
						alert.setContentText("table is full.");
					}

					//

					textField1.clear();
					textField2.clear();
					textField3.clear();
				}
			}

		});

		ResetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 표 초기화
		{
			@Override
			public void handle(MouseEvent arg0) {
				observableList.clear();
				obser.clear();
			}

		});

		RunButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 프로세스 실행
		{

			@Override
			public void handle(MouseEvent arg0) {

				obser.clear();// obser 초기화
				if (observableList.isEmpty()) // 프로세스 테이블이 비어있을 경우
				{
					warning("empty", "Process Table");
					return;
				}
				for (Process p : observableList)// 깊은 복사
				{
					obser.add(new Process(p.getProcessName(), p.getArrivalTime(), p.getBurstTime()));
				}
				Queue<Process> queue = new LinkedList<>();
				Queue<Process> endqueue=new LinkedList<>();
				String str = cbox.getValue(); // 체크박스에 있는 값 불러오기

				Ecore[] E = new Ecore[4];
				Pcore[] P = new Pcore[4];
				for (int i = 0; i < 4; i++) {
					E[i] = new Ecore();
					P[i] = new Pcore();
				}

				switch (str) {
				case "FCFS (First Come First Served)":
					for (int i = 1; i < Integer.MAX_VALUE; i++) // 실시간 처리형
					{
						for (Process p : obser) {
							if (p.getArrivalTime() == i - 1) // 현 시간과 Arrivaltime 같을 경우 q에 추가
								queue.add(p);
						}
						for (int j = 0; j < 4; j++) {
							if (!E[j].isVisit() && !queue.isEmpty())// q가 비어있지 않거나 e코어에 process가 없을 경우
							{
								E[j].setP(queue.poll());// 큐의 front에 잇는 값 pop
								E[j].setVisit(true);// 방문하는 걸로 체크
							}
						}

						for (int t = 0; t < 4; t++) {
							Process k=null;
							k=E[t].FCFS(i);// FCFS 하게함.
							if(k!=null)
								endqueue.add(k);
						}
						
						if(endqueue.size()==observableList.size()) 
							break;
					}
					break;

				case "RR (Round Robin)":
					String TimeQ = textField4.getText();
					if (TimeQ.trim().isEmpty() || TimeQ == null) // 타임쿼터스가 비어있을 경우
					{
						warning("empty", "TimeQ");
						textField4.clear();
						return;
					}
					if (!isInteger(TimeQ)) // 타임쿼터스가 숫자가 아닐 경우, 또는 오버플로우 언더플로우가 일어날 때
					{
						warning("number", "TimeQ");
						textField4.clear();
						return;
					}
					if (Integer.parseInt(TimeQ) < 0) // 음수 일 경우
					{
						warning("mnumber", "TimeQ");
						textField4.clear();
						return;
					}
					break;

				case "SPN":

					break;
				case "SRTN":

					break;

				case "HRRN":

					break;
				}
				;

				tableView2.setItems(obser);
			}
		});

	}

	private boolean isInteger(String str) // 문자열이 순자인지 판별
	{
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	private void warning(String str, String str1) // 위험 알리는 함수
	{
		if (str == "empty") {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("Empty error!!");
			alert.setContentText("The " + str1 + " is Empty.");

			alert.showAndWait();
		} // 해당 text 또는 list가 비어있을 경우
		else if (str == "number") {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("NumberFormat error!!");
			alert.setContentText("The " + str1 + " doesn't fit the number format.");

			alert.showAndWait();
		} // 숫자가 아닐 경우
		else if (str == "mnumber") {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!!");
			alert.setHeaderText("MinusNumber error!!");
			alert.setContentText("The " + str1 + " is Minus.");

			alert.showAndWait();
		} // 음수 일 경우

	}

}
