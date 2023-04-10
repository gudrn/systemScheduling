package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SampleController implements Initializable {

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
	private TableColumn<Process, Integer> ATColumn;

	@FXML
	private TableColumn<Process, Integer> BTColumn;

	@FXML
	private TableView<Process> tableView2;
	@FXML
	private TableColumn<Process, String> nameColumn1;

	@FXML
	private TableColumn<Process, Integer> ATColumn1;

	@FXML
	private TableColumn<Process, Integer> BTColumn1;

	@FXML
	private TableColumn<Process, Integer> WTColumn1;

	@FXML
	private TableColumn<Process, Integer> TTColumn1;

	@FXML
	private TableColumn<Process, Integer> NTTColumn1;

	ObservableList<Process> observableList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		cbox.getItems().removeAll(cbox.getItems());

		cbox.getItems().addAll("FCFS (First Come First Served)", "RR (Round Robin)", "SPN", "SRTN", "HRRN");// 셀 안에 있는
																											// 종류
		cbox.getSelectionModel().select("FCFS (First Come First Served))");// 셀 초기화
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

		AddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 표 데이터 입력
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

				if (!isInteger(str2) || !isInteger(str3)) // 타임쿼터스가 숫자가 아닐 경우 또는 오버플로우 언더플로우가 일어날 때
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
				if (!overflows(str2,str3)) {
					warning("number", "AT or BT");
					textField2.clear();
					textField3.clear();
					return;
				}

				tableView.getItems().add(new Process(str1, Integer.parseInt(str2), Integer.parseInt(str3)));//

				textField1.clear();
				textField2.clear();
				textField3.clear();
			}

			

		});

		ResetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 표 초기화
		{
			@Override
			public void handle(MouseEvent arg0) {
				observableList.clear();
			}

		});

		RunButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() // 프로세스 실행
		{

			@Override
			public void handle(MouseEvent arg0) {
				String str = cbox.getValue(); // 체크박스에 있는 값 불러오기

				if (observableList.isEmpty()) // 프로세스 테이블이 비어있을 경우
				{
					warning("empty", "Process Table");
					return;
				}

				switch (str) {
				case "FCFS (First Come First Served)":

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
				tableView2.setItems(observableList);
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
	
	private boolean overflows(String str2, String str3) // AT 와 BT에 대한 합 오버플로우 
	{
		if(Integer.parseInt(str2)>Integer.MAX_VALUE-Integer.parseInt(str3)) {
			return false;
		}
		if(Integer.parseInt(str3)>Integer.MIN_VALUE-Integer.parseInt(str2)) {
			return false;
		}
		return true;
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
