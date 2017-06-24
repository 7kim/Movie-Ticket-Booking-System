package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;

public class TheaterGUI extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField txtTicketNumberBook;
	private JTextField txtTicketIDSearchTicket;
	private JTextField txtTicketIDCancel;
	private JTextField txtMovieScoreSearchScore;
	private JTextField txtTicketNumberSearchSeat;
	private JTextField txtMovieTimeStartEarlySearchSeat;
	private JTextField txtMovieTimeLongMinSearchSeat;
	private JTextField txtMovieTimeStartLateSearchSeat;
	private JTextField txtMovieTimeLongMaxSearchSeat;
	
	private TheaterGUIController ctl = new TheaterGUIController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TheaterGUI frame = new TheaterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TheaterGUI() {
		setTitle("\u96FB\u5F71\u8A02\u7968\u7CFB\u7D71");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tabbedPane.setBounds(0, 0, 534, 500);
		contentPane.add(tabbedPane);
		
		JPanel BookPanel = new JPanel();
		tabbedPane.addTab("\u8A02\u7968", null, BookPanel, null);
		BookPanel.setLayout(null);
		
		JLabel lblUserID = new JLabel("\u6703\u54E1\u7DE8\u865F\uFF1A");
		lblUserID.setBounds(40, 10, 75, 15);
		BookPanel.add(lblUserID);
		
		JComboBox cbbUserID = new JComboBox(ctl.getAllUserID());
		cbbUserID.setBounds(116, 7, 100, 21);
		BookPanel.add(cbbUserID);
		
		JSeparator separator0Book = new JSeparator();
		separator0Book.setBounds(10, 35, 509, 2);
		BookPanel.add(separator0Book);
		
		JLabel lblMovieNameBook = new JLabel("\u96FB\u5F71\u540D\u7A31\uFF1A");
		lblMovieNameBook.setBounds(40, 47, 75, 15);
		BookPanel.add(lblMovieNameBook);
		
		JComboBox cbbMovieNameBook = new JComboBox(ctl.getAllMovieNames());
		cbbMovieNameBook.setBounds(116, 44, 300, 21);
		BookPanel.add(cbbMovieNameBook);
		
		JLabel lblMovieTimeBook = new JLabel();
		lblMovieTimeBook.setText("\u653E\u5F71\u5834\u6B21\uFF1A");
		lblMovieTimeBook.setBounds(40, 72, 75, 15);
		BookPanel.add(lblMovieTimeBook);
		
		JComboBox cbbMovieTimeBook = new JComboBox();
		DefaultComboBoxModel[] cbbMovieTimeBookModel = new DefaultComboBoxModel[ctl.getAllMovieNames().length]; 
		for (int i = 0; i < ctl.getAllMovieNames().length; i++) {
			cbbMovieTimeBookModel[i] = new DefaultComboBoxModel(ctl.getMovieTime(i));	
		}
		cbbMovieTimeBook.setModel(cbbMovieTimeBookModel[0]);
		cbbMovieTimeBook.setBounds(116, 69, 100, 21);
		BookPanel.add(cbbMovieTimeBook);
				
		JLabel lblTicketNumberBook = new JLabel("\u8A02\u7968\u5F35\u6578\uFF1A");
		lblTicketNumberBook.setBounds(40, 97, 75, 15);
		BookPanel.add(lblTicketNumberBook);
		
		txtTicketNumberBook = new JTextField();
		txtTicketNumberBook.setColumns(10);
		txtTicketNumberBook.setBounds(116, 94, 100, 21);
		BookPanel.add(txtTicketNumberBook);
		
		JSeparator separator1Book = new JSeparator();
		separator1Book.setBounds(10, 125, 509, 2);
		BookPanel.add(separator1Book);
		
		JCheckBox ckbSeatAreaBook = new JCheckBox("\u6307\u5B9A\u5340\u57DF\uFF1A");
		ckbSeatAreaBook.setBounds(20, 133, 90, 23);
		BookPanel.add(ckbSeatAreaBook);
		
		JComboBox cbbSeatAreaBook = new JComboBox();
		DefaultComboBoxModel seatAreaSpecifiedComboBoxModel = new DefaultComboBoxModel(ctl.getAllAreas()); 
		cbbSeatAreaBook.setModel(seatAreaSpecifiedComboBoxModel);
		cbbSeatAreaBook.setSelectedIndex(0);
		cbbSeatAreaBook.setBounds(116, 134, 100, 21);
		cbbSeatAreaBook.setVisible(false);
		BookPanel.add(cbbSeatAreaBook);
		
		JCheckBox ckbSeatRowBook = new JCheckBox("\u6307\u5B9A\u6392\u6578\uFF1A");
		ckbSeatRowBook.setBounds(20, 158, 90, 23);
		BookPanel.add(ckbSeatRowBook);
		
		JComboBox cbbSeatRowBook = new JComboBox();
		DefaultComboBoxModel cbbSeatRowModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(0));
		cbbSeatRowBook.setModel(cbbSeatRowModel);
		cbbSeatRowBook.setSelectedIndex(0);
		cbbSeatRowBook.setBounds(116, 159, 100, 21);
		cbbSeatRowBook.setVisible(false);
		BookPanel.add(cbbSeatRowBook);
		
		JCheckBox ckbSeatContBook = new JCheckBox("\u540C\u6392\u9023\u7E8C");
		ckbSeatContBook.setBounds(20, 183, 90, 23);
		BookPanel.add(ckbSeatContBook);
		
		JSeparator separator2Book = new JSeparator();
		separator2Book.setBounds(10, 212, 509, 2);
		BookPanel.add(separator2Book);
		
		JButton btnBook = new JButton("\u8A02\u7968");
		btnBook.setBounds(270, 183, 87, 23);
		BookPanel.add(btnBook);
		
		JTextArea txtTicketIDBook = new JTextArea ( 1, 1 );
		txtTicketIDBook.setTabSize(4);
		
		JScrollPane scrTicketIDBook = new JScrollPane(txtTicketIDBook);
		scrTicketIDBook.setBounds(20, 224, 499, 150);
		BookPanel.add(scrTicketIDBook);
		
		JPanel SearchTicketPanel = new JPanel();
		tabbedPane.addTab("\u67E5\u8A62\u8A02\u7968", null, SearchTicketPanel, null);
		SearchTicketPanel.setLayout(null);
		
		JLabel lblTicketIDSearchTicket = new JLabel("\u96FB\u5F71\u7968\u7DE8\u865F\uFF1A");
		lblTicketIDSearchTicket.setBounds(10, 10, 80, 15);
		SearchTicketPanel.add(lblTicketIDSearchTicket);
		
		txtTicketIDSearchTicket = new JTextField();
		txtTicketIDSearchTicket.setBounds(100, 7, 100, 21);
		SearchTicketPanel.add(txtTicketIDSearchTicket);
		txtTicketIDSearchTicket.setColumns(10);
		
		JButton btnSearchTicket = new JButton("\u67E5\u8A62");
		btnSearchTicket.setBounds(210, 6, 87, 23);
		SearchTicketPanel.add(btnSearchTicket);
		
		JSeparator separatorSearchTicket = new JSeparator();
		separatorSearchTicket.setBounds(10, 35, 509, 2);
		SearchTicketPanel.add(separatorSearchTicket);
		
		JLabel lblMovieNameSearchTicket = new JLabel("\u96FB\u5F71\u540D\u7A31\uFF1A");
		lblMovieNameSearchTicket.setBounds(10, 47, 80, 15);
		SearchTicketPanel.add(lblMovieNameSearchTicket);
		
		JLabel lblTicketMovieNameSearchTicket = new JLabel("TicketMovieName");
		lblTicketMovieNameSearchTicket.setBounds(100, 47, 300, 15);
		SearchTicketPanel.add(lblTicketMovieNameSearchTicket);
		
		JLabel lblMovieTimeSearchTicket = new JLabel("\u653E\u6620\u5834\u6B21\uFF1A");
		lblMovieTimeSearchTicket.setBounds(10, 72, 80, 15);
		SearchTicketPanel.add(lblMovieTimeSearchTicket);
		
		JLabel lblTicketMovieTimeSearchTicket = new JLabel("TicketMovieTime");
		lblTicketMovieTimeSearchTicket.setBounds(100, 72, 300, 15);
		SearchTicketPanel.add(lblTicketMovieTimeSearchTicket);
		
		JLabel lblMovieHallSearchTicket = new JLabel("\u653E\u6620\u5F71\u5EF3\uFF1A");
		lblMovieHallSearchTicket.setBounds(10, 97, 80, 15);
		SearchTicketPanel.add(lblMovieHallSearchTicket);
		
		JLabel lblTicketMovieHallSearchTicket = new JLabel("TicketMovieHall");
		lblTicketMovieHallSearchTicket.setBounds(100, 97, 300, 15);
		SearchTicketPanel.add(lblTicketMovieHallSearchTicket);
		
		JLabel lblSeatSearchTicket = new JLabel("\u5EA7\u4F4D\u7DE8\u865F\uFF1A");
		lblSeatSearchTicket.setBounds(10, 122, 80, 15);
		SearchTicketPanel.add(lblSeatSearchTicket);
		
		JLabel lblTicketSeatSearchTicket = new JLabel("TicketSeat");
		lblTicketSeatSearchTicket.setBounds(100, 122, 300, 15);
		SearchTicketPanel.add(lblTicketSeatSearchTicket);
		
		JPanel CancelPanel = new JPanel();
		tabbedPane.addTab("\u9000\u7968", null, CancelPanel, null);
		CancelPanel.setLayout(null);
		
		JLabel lblTicketIDCancel = new JLabel("\u96FB\u5F71\u7968\u7DE8\u865F\uFF1A");
		lblTicketIDCancel.setBounds(10, 10, 80, 15);
		CancelPanel.add(lblTicketIDCancel);
		
		txtTicketIDCancel = new JTextField();
		txtTicketIDCancel.setBounds(100, 7, 100, 21);
		CancelPanel.add(txtTicketIDCancel);
		txtTicketIDCancel.setColumns(10);
		
		JButton btnCancel = new JButton("\u9000\u7968");
		btnCancel.setBounds(210, 6, 87, 23);
		CancelPanel.add(btnCancel);
		
		JSeparator separatorCancel = new JSeparator();
		separatorCancel.setBounds(10, 35, 509, 2);
		CancelPanel.add(separatorCancel);
		
		JLabel lblMsgCancel = new JLabel("CancelMsg");
		lblMsgCancel.setHorizontalAlignment(SwingConstants.LEFT);
		lblMsgCancel.setForeground(Color.BLACK);
		lblMsgCancel.setBounds(10, 47, 300, 15);
		CancelPanel.add(lblMsgCancel);
		
		JPanel SearchScorePanel = new JPanel();
		tabbedPane.addTab("\u67E5\u8A55\u50F9", null, SearchScorePanel, null);
		SearchScorePanel.setLayout(null);
		
		JLabel lblMovieScoreSearchScore = new JLabel("\u7DB2\u53CB\u8A55\u5206\uFF1A");
		lblMovieScoreSearchScore.setBounds(10, 13, 75, 15);
		SearchScorePanel.add(lblMovieScoreSearchScore);
		
		txtMovieScoreSearchScore = new JTextField();
		txtMovieScoreSearchScore.setBounds(95, 10, 100, 21);
		SearchScorePanel.add(txtMovieScoreSearchScore);
		txtMovieScoreSearchScore.setColumns(10);
		
		JLabel lblMovieScoreUnitSearchScore = new JLabel("\u5206\u4EE5\u4E0A");
		lblMovieScoreUnitSearchScore.setBounds(205, 13, 46, 15);
		SearchScorePanel.add(lblMovieScoreUnitSearchScore);
		
		JButton btnSearchScore = new JButton("\u641C\u5C0B");
		btnSearchScore.setBounds(261, 9, 87, 23);
		SearchScorePanel.add(btnSearchScore);
		
		JSeparator separatorSearchScore = new JSeparator();
		separatorSearchScore.setBounds(10, 38, 509, 2);
		SearchScorePanel.add(separatorSearchScore);
		
		JTextArea txtMovieSearchScore = new JTextArea(5, 1);
		txtMovieSearchScore.setTabSize(4);
		
		JScrollPane scrMovieSearchScore = new JScrollPane(txtMovieSearchScore);
		scrMovieSearchScore.setBounds(10, 50, 509, 200);
		SearchScorePanel.add(scrMovieSearchScore);
		
		JPanel SearchMoviePanel = new JPanel();
		tabbedPane.addTab("\u627E\u96FB\u5F71", null, SearchMoviePanel, null);
		SearchMoviePanel.setLayout(null);
		
		JLabel lblMovieNameSearchMovie = new JLabel("\u96FB\u5F71\u540D\u7A31\uFF1A");
		lblMovieNameSearchMovie.setBounds(10, 13, 70, 15);
		SearchMoviePanel.add(lblMovieNameSearchMovie);
		
		JComboBox cbbMovieNameSearchMovie = new JComboBox(ctl.getAllMovieNames());
		cbbMovieNameSearchMovie.setBounds(90, 10, 300, 21);
		cbbMovieNameSearchMovie.setSelectedIndex(-1);
		SearchMoviePanel.add(cbbMovieNameSearchMovie);
		
		JSeparator separatorSearchMovie = new JSeparator();
		separatorSearchMovie.setBounds(10, 38, 459, 2);
		SearchMoviePanel.add(separatorSearchMovie);
		
		JLabel lblMovieRateSearchMovie = new JLabel("\u96FB\u5F71\u5206\u7D1A\uFF1A");
		lblMovieRateSearchMovie.setBounds(10, 50, 75, 15);
		SearchMoviePanel.add(lblMovieRateSearchMovie);
		
		JLabel lblSearchMovieRateSearchMovie = new JLabel("MovieRate");
		lblSearchMovieRateSearchMovie.setBounds(95, 50, 400, 15);
		SearchMoviePanel.add(lblSearchMovieRateSearchMovie);
		
		JLabel lblMovieTimeSearchMovie = new JLabel("\u653E\u6620\u5834\u6B21\uFF1A");
		lblMovieTimeSearchMovie.setBounds(10, 73, 75, 15);
		SearchMoviePanel.add(lblMovieTimeSearchMovie);
		
		JLabel lblSearchMovieTimeSearchMovie = new JLabel("MovieTime");
		lblSearchMovieTimeSearchMovie.setBounds(95, 73, 400, 15);
		SearchMoviePanel.add(lblSearchMovieTimeSearchMovie);
		
		JLabel lblMovieHallSearchMovie = new JLabel("\u653E\u6620\u5F71\u5EF3\uFF1A");
		lblMovieHallSearchMovie.setBounds(10, 98, 75, 15);
		SearchMoviePanel.add(lblMovieHallSearchMovie);
		
		JLabel lblSearchMovieHallSearchMovie = new JLabel("MovieHall");
		lblSearchMovieHallSearchMovie.setBounds(95, 98, 400, 15);
		SearchMoviePanel.add(lblSearchMovieHallSearchMovie);
		
		JPanel SearchSeatPanel = new JPanel();
		tabbedPane.addTab("\u627E\u5EA7\u4F4D", null, SearchSeatPanel, null);
		SearchSeatPanel.setLayout(null);
		
		JLabel lblTicketNumberSearchSeat = new JLabel("\u9700\u6C42\u5F35\u6578\uFF1A");
		lblTicketNumberSearchSeat.setBounds(30, 10, 80, 15);
		SearchSeatPanel.add(lblTicketNumberSearchSeat);
		
		txtTicketNumberSearchSeat = new JTextField();
		txtTicketNumberSearchSeat.setBounds(100, 7, 100, 21);
		SearchSeatPanel.add(txtTicketNumberSearchSeat);
		txtTicketNumberSearchSeat.setColumns(10);
		
		JLabel lblMovieTimeSearchSeat = new JLabel("\u958B\u64AD\u6642\u9593\uFF1A");
		lblMovieTimeSearchSeat.setBounds(30, 38, 80, 15);
		SearchSeatPanel.add(lblMovieTimeSearchSeat);
		
		txtMovieTimeStartEarlySearchSeat = new JTextField();
		txtMovieTimeStartEarlySearchSeat.setColumns(10);
		txtMovieTimeStartEarlySearchSeat.setBounds(100, 35, 100, 21);
		SearchSeatPanel.add(txtMovieTimeStartEarlySearchSeat);
		
		JLabel lblMovieTimeToSearchSeat = new JLabel("~");
		lblMovieTimeToSearchSeat.setBounds(210, 38, 15, 15);
		SearchSeatPanel.add(lblMovieTimeToSearchSeat);
		
		txtMovieTimeStartLateSearchSeat = new JTextField();
		txtMovieTimeStartLateSearchSeat.setColumns(10);
		txtMovieTimeStartLateSearchSeat.setBounds(235, 35, 100, 21);
		SearchSeatPanel.add(txtMovieTimeStartLateSearchSeat);
		
		JLabel lblMovieTimeLongSearchSeat = new JLabel("\u7247\u9577\uFF1A");
		lblMovieTimeLongSearchSeat.setBounds(30, 66, 75, 15);
		SearchSeatPanel.add(lblMovieTimeLongSearchSeat);
		
		txtMovieTimeLongMinSearchSeat = new JTextField();
		txtMovieTimeLongMinSearchSeat.setColumns(10);
		txtMovieTimeLongMinSearchSeat.setBounds(100, 63, 100, 21);
		SearchSeatPanel.add(txtMovieTimeLongMinSearchSeat);
		
		JLabel lblMovieTimeLongToSearchSeat = new JLabel("~");
		lblMovieTimeLongToSearchSeat.setBounds(210, 66, 15, 15);
		SearchSeatPanel.add(lblMovieTimeLongToSearchSeat);
		
		txtMovieTimeLongMaxSearchSeat = new JTextField();
		txtMovieTimeLongMaxSearchSeat.setColumns(10);
		txtMovieTimeLongMaxSearchSeat.setBounds(235, 63, 100, 21);
		SearchSeatPanel.add(txtMovieTimeLongMaxSearchSeat);
		
		JLabel lblMovieTimeLongUnitSearchSeat = new JLabel("\u5206\u9418");
		lblMovieTimeLongUnitSearchSeat.setBounds(345, 66, 50, 15);
		SearchSeatPanel.add(lblMovieTimeLongUnitSearchSeat);
		
		JSeparator separator0SearchSeat = new JSeparator();
		separator0SearchSeat.setBounds(10, 91, 509, 2);
		SearchSeatPanel.add(separator0SearchSeat);
		
		JCheckBox ckbSeatContSearchSeat = new JCheckBox("\u540C\u6392\u9023\u7E8C");
		ckbSeatContSearchSeat.setBounds(10, 149, 90, 23);
		SearchSeatPanel.add(ckbSeatContSearchSeat);
		
		JCheckBox ckbSeatAreaSearchSeat = new JCheckBox("\u6307\u5B9A\u5340\u57DF\uFF1A");
		ckbSeatAreaSearchSeat.setBounds(10, 99, 90, 23);
		SearchSeatPanel.add(ckbSeatAreaSearchSeat);
		
		JComboBox cbbSeatAreaSearchSeat = new JComboBox();
		cbbSeatAreaSearchSeat.setModel(seatAreaSpecifiedComboBoxModel);
		cbbSeatAreaSearchSeat.setBounds(100, 100, 100, 21);
		cbbSeatAreaSearchSeat.setVisible(false);
		SearchSeatPanel.add(cbbSeatAreaSearchSeat);
		BookPanel.add(cbbSeatAreaBook);
		
		JCheckBox ckbSeatRowSearchSeat = new JCheckBox("\u6307\u5B9A\u6392\u6578\uFF1A");
		ckbSeatRowSearchSeat.setBounds(10, 124, 90, 23);
		SearchSeatPanel.add(ckbSeatRowSearchSeat);
		
		JComboBox cbbSeatRowSearchSeat = new JComboBox();
		cbbSeatRowSearchSeat.setBounds(100, 125, 100, 21);
		cbbSeatRowSearchSeat.setVisible(false);
		SearchSeatPanel.add(cbbSeatRowSearchSeat);
		
		JButton btnSearchSeat = new JButton("\u641C\u5C0B");
		btnSearchSeat.setBounds(392, 149, 87, 23);
		SearchSeatPanel.add(btnSearchSeat);
		
		JSeparator separator1SearchSeat = new JSeparator();
		separator1SearchSeat.setBounds(10, 178, 509, 2);
		SearchSeatPanel.add(separator1SearchSeat);
		
		JTextArea txtSeatableMoviesSearchSeat = new JTextArea();
		
		JScrollPane scrMsgSearchSeat = new JScrollPane(txtSeatableMoviesSearchSeat);
		scrMsgSearchSeat.setBounds(10, 189, 509, 81);
		SearchSeatPanel.add(scrMsgSearchSeat);
		
		
//		TODO
		
		cbbMovieNameBook.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbbMovieTimeBook.setModel(cbbMovieTimeBookModel[cbbMovieNameBook.getSelectedIndex()]);
				if (ckbSeatRowBook.isSelected()) {
					DefaultComboBoxModel cbbSeatRowModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(cbbMovieNameBook.getSelectedIndex()));
					cbbSeatRowBook.setModel(cbbSeatRowModel);	
				}
			}
		});

		ckbSeatAreaBook.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ckbSeatAreaBook.isSelected()) {
					cbbSeatAreaBook.setVisible(true);
					cbbSeatAreaBook.setSelectedIndex(0);
					if (ckbSeatRowBook.isSelected()) {
						DefaultComboBoxModel cbbSeatRowBookModel = new DefaultComboBoxModel(ctl.getAreaRows(cbbSeatAreaBook.getSelectedItem().toString()));
						cbbSeatRowBook.setModel(cbbSeatRowBookModel);
					}
				} else {
					cbbSeatAreaBook.setVisible(false);
					if (ckbSeatRowBook.isSelected()) {
						DefaultComboBoxModel cbbSeatRowBookModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(cbbMovieNameBook.getSelectedIndex()));
						cbbSeatRowBook.setModel(cbbSeatRowBookModel);
					}

				}
			}
		});
		
		ckbSeatRowBook.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ckbSeatRowBook.isSelected()) {
					if (ckbSeatAreaBook.isSelected()) {
						DefaultComboBoxModel cbbSeatRowBookModel = new DefaultComboBoxModel(ctl.getAreaRows(cbbSeatAreaBook.getSelectedItem().toString()));
						cbbSeatRowBook.setModel(cbbSeatRowBookModel);
						cbbSeatRowBook.setSelectedIndex(0);
						cbbSeatRowBook.setVisible(true);
					} else {
						DefaultComboBoxModel cbbSeatRowBookModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(cbbMovieNameBook.getSelectedIndex()));
						cbbSeatRowBook.setModel(cbbSeatRowBookModel);
						cbbSeatRowBook.setSelectedIndex(0);
						cbbSeatRowBook.setVisible(true);
					}
				} else {
					cbbSeatRowBook.setVisible(false);
				}
			}
		});
		
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtTicketIDBook.setForeground(Color.BLACK);
					txtTicketIDBook.setText(ctl.book(cbbUserID.getSelectedIndex(), cbbMovieNameBook.getSelectedIndex(), 
							cbbMovieTimeBook.getSelectedItem().toString(), ckbSeatContBook.isSelected(),
							ckbSeatRowBook.isSelected(), cbbSeatRowBook.getSelectedItem().toString(),
							ckbSeatAreaBook.isSelected(), cbbSeatAreaBook.getSelectedItem().toString(),
							txtTicketNumberBook.getText()));
				} catch (Exception err) {
					System.out.println("error");
					txtTicketIDBook.setForeground(Color.RED);
					txtTicketIDBook.setText(err.getMessage());
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblMsgCancel.setForeground(Color.BLACK);
					lblMsgCancel.setText(ctl.cancel(txtTicketIDCancel.getText()));
				} catch (Exception err) {
					lblMsgCancel.setForeground(Color.RED);
					lblMsgCancel.setText(err.getMessage());
				}
			}
		});

		btnSearchTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblTicketMovieNameSearchTicket.setForeground(Color.BLACK);
					lblTicketMovieNameSearchTicket.setText(ctl.getTicketMovieName(txtTicketIDSearchTicket.getText()));
					lblTicketMovieTimeSearchTicket.setText(ctl.getTicketMovieTime(txtTicketIDSearchTicket.getText()));
					lblTicketMovieHallSearchTicket.setText(ctl.getTicketMovieHall(txtTicketIDSearchTicket.getText()));
					lblTicketSeatSearchTicket.setText(ctl.getTicketSeat(txtTicketIDSearchTicket.getText()));
				} catch (Exception err) {
					lblTicketMovieNameSearchTicket.setForeground(Color.RED);
					lblTicketMovieNameSearchTicket.setText(err.getMessage());
					lblTicketMovieTimeSearchTicket.setText("");
					lblTicketMovieHallSearchTicket.setText("");
					lblTicketSeatSearchTicket.setText("");
				}
			}
		});
		
		btnSearchScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtMovieSearchScore.setForeground(Color.BLACK);
					txtMovieSearchScore.setText(ctl.getScoredMovies(txtMovieScoreSearchScore.getText()));	
				} catch (Exception err) {
					txtMovieSearchScore.setForeground(Color.RED);
					txtMovieSearchScore.setText(err.getMessage());
				}
			}
		});

		cbbMovieNameSearchMovie.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					lblSearchMovieRateSearchMovie.setForeground(Color.BLACK);
					lblSearchMovieRateSearchMovie.setText(ctl.getSearchMovieRate(cbbMovieNameSearchMovie.getSelectedIndex()));
					lblSearchMovieTimeSearchMovie.setText(ctl.getSearchMovieTime(cbbMovieNameSearchMovie.getSelectedIndex()));
					lblSearchMovieHallSearchMovie.setText(ctl.getSearchMovieHall(cbbMovieNameSearchMovie.getSelectedIndex()));	
				} catch (Exception err) {
					lblSearchMovieRateSearchMovie.setForeground(Color.RED);
					lblSearchMovieRateSearchMovie.setText(err.getMessage());
				}
			}
		});

//		TODO
		ckbSeatAreaSearchSeat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ckbSeatAreaSearchSeat.isSelected()) {
					cbbSeatAreaSearchSeat.setVisible(true);
					cbbSeatAreaSearchSeat.setSelectedIndex(0);
					if (ckbSeatRowSearchSeat.isSelected()) {
						DefaultComboBoxModel cbbSeatRowSearchSeatModel = new DefaultComboBoxModel(ctl.getAreaRows(cbbSeatAreaBook.getSelectedItem().toString()));
						cbbSeatRowSearchSeat.setModel(cbbSeatRowSearchSeatModel);
						cbbSeatRowSearchSeat.setSelectedIndex(0);
					}
				} else {
					cbbSeatAreaSearchSeat.setVisible(false);
					if (ckbSeatRowSearchSeat.isSelected()) {
						DefaultComboBoxModel cbbSeatRowSearchSeatModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(0));
						cbbSeatRowSearchSeat.setModel(cbbSeatRowSearchSeatModel);
						cbbSeatRowSearchSeat.setSelectedIndex(0);
					}
				}
			}
		});

		ckbSeatRowSearchSeat.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (ckbSeatRowSearchSeat.isSelected()) {
					if (ckbSeatAreaSearchSeat.isSelected()) {
						DefaultComboBoxModel cbbSeatRowSearchSeatModel = new DefaultComboBoxModel(ctl.getAreaRows(cbbSeatAreaSearchSeat.getSelectedItem().toString()));
						cbbSeatRowSearchSeat.setModel(cbbSeatRowSearchSeatModel);
						cbbSeatRowSearchSeat.setSelectedIndex(0);
						cbbSeatRowSearchSeat.setVisible(true);
					} else {
						DefaultComboBoxModel cbbSeatRowSearchSeatModel = new DefaultComboBoxModel(ctl.getMovieHallAllRows(0));
						cbbSeatRowSearchSeat.setModel(cbbSeatRowSearchSeatModel);
						cbbSeatRowSearchSeat.setSelectedIndex(0);
						cbbSeatRowSearchSeat.setVisible(true);
					}
				} else {
					cbbSeatRowSearchSeat.setVisible(false);
				}	
			}
		});
		
		btnSearchSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtSeatableMoviesSearchSeat.setForeground(Color.BLACK);
					txtSeatableMoviesSearchSeat.setText(ctl.getSeatableMovies(txtTicketNumberSearchSeat.getText(), txtMovieTimeStartEarlySearchSeat.getText(), txtMovieTimeStartLateSearchSeat.getText(), txtMovieTimeLongMinSearchSeat.getText(), txtMovieTimeLongMaxSearchSeat.getText()));
				} catch (Exception err) {
					txtSeatableMoviesSearchSeat.setForeground(Color.RED);
					txtSeatableMoviesSearchSeat.setText(err.getMessage());
				}
			}
		});

	}
}
