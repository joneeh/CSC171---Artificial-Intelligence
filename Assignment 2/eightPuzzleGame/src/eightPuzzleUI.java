import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Toolkit;
import javax.swing.JRadioButton;

public class eightPuzzleUI {

	private JFrame frmpuzzleSolveIt;
	private JTextField in_node;

	private CheckAnswer chk = new CheckAnswer();
	private Compare cmpr = new Compare();
	private SwapLaw sw = new SwapLaw();
	
	private int node = 1000;
	Mem[] mem = new Mem[0];
	
	private int[] nxt = new int[1000];
	private int far;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eightPuzzleUI window = new eightPuzzleUI();
					window.frmpuzzleSolveIt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public eightPuzzleUI() {
		initialize();
	}

	private void initialize() {
		frmpuzzleSolveIt = new JFrame();
		frmpuzzleSolveIt.setTitle("8 PUZZLE GAME SOLVER USING BREADTH FIRST SEARCH OR A* ALGORITHM");
		frmpuzzleSolveIt.setBounds(100, 100, 750, 482);
		frmpuzzleSolveIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmpuzzleSolveIt.getContentPane().setLayout(null);
		
		JButton btn_0 = new JButton("");
		JButton btn_1 = new JButton("1");
		JButton btn_2 = new JButton("2");
		JButton btn_3 = new JButton("3");
		JButton btn_4 = new JButton("4");
		JButton btn_5 = new JButton("5");
		JButton btn_6 = new JButton("6");
		JButton btn_7 = new JButton("7");
		JButton btn_8 = new JButton("8");
		JButton btn_reset = new JButton("Reset");
		JLabel finish_dis = new JLabel("");
		JButton btn_roll = new JButton("Undo");
		JRadioButton dot_astar = new JRadioButton("A*");
		JRadioButton dot_bfs = new JRadioButton("BFS");
		
		btn_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = btn_0.getText();
				
				if(btn_1.getText().equals(""))
				{
					btn_0.setText(btn_1.getText());
					btn_1.setText(temp);
				}
				else if(btn_3.getText().equals(""))
				{
					btn_0.setText(btn_3.getText());
					btn_3.setText(temp);
				}
			}
		});
		btn_0.setFont(new Font("Arial", Font.BOLD, 40));
		btn_0.setBounds(12, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_0);
		btn_0.setBackground(Color.WHITE);
		
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_1.getText();
				
				if(btn_0.getText().equals(""))
				{
					btn_1.setText(btn_0.getText());
					btn_0.setText(temp);
				}
				else if(btn_2.getText().equals(""))
				{
					btn_1.setText(btn_2.getText());
					btn_2.setText(temp);
				}
				else if(btn_4.getText().equals(""))
				{
					btn_1.setText(btn_4.getText());
					btn_4.setText(temp);
				}
			}
		});
		btn_1.setFont(new Font("Arial", Font.BOLD, 50));
		btn_1.setBounds(124, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_1);
		btn_1.setBackground(Color.WHITE);

		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = btn_2.getText();
				
				if(btn_1.getText().equals(""))
				{
					btn_2.setText(btn_1.getText());
					btn_1.setText(temp);
				}
				else if(btn_5.getText().equals(""))
				{
					btn_2.setText(btn_5.getText());
					btn_5.setText(temp);
				}
			}
		});
		btn_2.setFont(new Font("Arial", Font.BOLD, 50));
		btn_2.setBounds(236, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_2);
		btn_2.setBackground(Color.WHITE);
		
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_3.getText();
				
				if(btn_0.getText().equals(""))
				{
					btn_3.setText(btn_0.getText());
					btn_0.setText(temp);
				}
				else if(btn_6.getText().equals(""))
				{
					btn_3.setText(btn_6.getText());
					btn_6.setText(temp);
				}
				else if(btn_4.getText().equals(""))
				{
					btn_3.setText(btn_4.getText());
					btn_4.setText(temp);
				}
			}
		});
		btn_3.setFont(new Font("Arial", Font.BOLD, 50));
		btn_3.setBounds(12, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_3);
		btn_3.setBackground(Color.WHITE);

		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_4.getText();
				
				if(btn_1.getText().equals(""))
				{
					btn_4.setText(btn_1.getText());
					btn_1.setText(temp);
				}
				else if(btn_3.getText().equals(""))
				{
					btn_4.setText(btn_3.getText());
					btn_3.setText(temp);
				}
				else if(btn_5.getText().equals(""))
				{
					btn_4.setText(btn_5.getText());
					btn_5.setText(temp);
				}
				else if(btn_7.getText().equals(""))
				{
					btn_4.setText(btn_7.getText());
					btn_7.setText(temp);
				}
			}
		});
		btn_4.setFont(new Font("Arial", Font.BOLD, 50));
		btn_4.setBounds(124, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_4);
		btn_4.setBackground(Color.WHITE);

		btn_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_5.getText();
				
				if(btn_8.getText().equals(""))
				{
					btn_5.setText(btn_8.getText());
					btn_8.setText(temp);
				}
				else if(btn_2.getText().equals(""))
				{
					btn_5.setText(btn_2.getText());
					btn_2.setText(temp);
				}
				else if(btn_4.getText().equals(""))
				{
					btn_5.setText(btn_4.getText());
					btn_4.setText(temp);
				}
			}
		});
		btn_5.setFont(new Font("Arial", Font.BOLD, 50));
		btn_5.setBounds(236, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_5);
		btn_5.setBackground(Color.WHITE);
		
		btn_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_6.getText();
				
				if(btn_3.getText().equals(""))
				{
					btn_6.setText(btn_3.getText());
					btn_3.setText(temp);
				}
				else if(btn_7.getText().equals(""))
				{
					btn_6.setText(btn_7.getText());
					btn_7.setText(temp);
				}
			}
		});
		btn_6.setFont(new Font("Arial", Font.BOLD, 50));
		btn_6.setBounds(12, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_6);
		btn_6.setBackground(Color.WHITE);

		btn_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = btn_7.getText();
				
				if(btn_8.getText().equals(""))
				{
					btn_7.setText(btn_8.getText());
					btn_8.setText(temp);
				}
				else if(btn_6.getText().equals(""))
				{
					btn_7.setText(btn_6.getText());
					btn_6.setText(temp);
				}
				else if(btn_4.getText().equals(""))
				{
					btn_7.setText(btn_4.getText());
					btn_4.setText(temp);
				}
			}
		});
		btn_7.setFont(new Font("Arial", Font.BOLD, 50));
		btn_7.setBounds(124, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_7);
		btn_7.setBackground(Color.WHITE);

		btn_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = btn_8.getText();
				
				if(btn_5.getText().equals(""))
				{
					btn_8.setText(btn_5.getText());
					btn_5.setText(temp);
				}
				else if(btn_7.getText().equals(""))
				{
					btn_8.setText(btn_7.getText());
					btn_7.setText(temp);
				}
			}
		});
		btn_8.setFont(new Font("Arial", Font.BOLD, 50));
		btn_8.setBounds(236, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(btn_8);
		btn_8.setBackground(Color.WHITE);
		
		
		JLabel lblMaxNodes = new JLabel("Max Nodes");
		lblMaxNodes.setForeground(Color.BLACK);
		lblMaxNodes.setFont(new Font("Arial", Font.PLAIN, 25));
		lblMaxNodes.setBounds(348, 13, 182, 50);
		frmpuzzleSolveIt.getContentPane().add(lblMaxNodes);
		
		in_node = new JTextField();
		in_node.setFont(new Font("Arial", Font.PLAIN, 25));
		in_node.setForeground(Color.BLACK);
		in_node.setText("1000");
		in_node.setHorizontalAlignment(SwingConstants.RIGHT);
		in_node.setBounds(348, 63, 182, 50);
		frmpuzzleSolveIt.getContentPane().add(in_node);
		in_node.setColumns(10);
		
		JLabel lblSpreadedNodes = new JLabel("Spreaded Nodes");
		lblSpreadedNodes.setForeground(Color.BLACK);
		lblSpreadedNodes.setFont(new Font("Arial", Font.PLAIN, 25));
		lblSpreadedNodes.setBounds(348, 126, 182, 50);
		frmpuzzleSolveIt.getContentPane().add(lblSpreadedNodes);
		
		JLabel sprd_node = new JLabel("0");
		sprd_node.setHorizontalAlignment(SwingConstants.RIGHT);
		sprd_node.setForeground(Color.BLACK);
		sprd_node.setFont(new Font("Arial", Font.PLAIN, 25));
		sprd_node.setBounds(348, 176, 182, 50);
		frmpuzzleSolveIt.getContentPane().add(sprd_node);
		
		JLabel algo = new JLabel("Algorithms");
		algo.setBounds(400, 200, 180, 50);
		algo.setForeground(Color.BLACK);
		algo.setFont(new Font("Arial", Font.PLAIN, 20));
		frmpuzzleSolveIt.getContentPane().add(algo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(542, 13, 182, 326);
		frmpuzzleSolveIt.getContentPane().add(scrollPane);
		
		JTextPane text_dis = new JTextPane();
		text_dis.setFont(new Font("Arial", Font.PLAIN, 25));
		text_dis.setText("test");
		text_dis.setEditable(false);
		text_dis.setText(">>> Goal >>> Click BFS or A* First");
		scrollPane.setViewportView(text_dis);
		
		

		dot_bfs.setFont(new Font("Arial", Font.BOLD, 30));
		dot_bfs.setBounds(435, 239, 101, 48);
		frmpuzzleSolveIt.getContentPane().add(dot_bfs);
		
		
		dot_astar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dot_bfs.setSelected(false);
			}
		});
		dot_astar.setFont(new Font("Arial", Font.BOLD, 30));
		dot_astar.setBounds(365, 239, 65, 47);
		frmpuzzleSolveIt.getContentPane().add(dot_astar);
		
		
		JButton button = new JButton("");
		JButton button_1 = new JButton("1");
		JButton button_2 = new JButton("2");
		JButton button_3 = new JButton("3");
		JButton button_4 = new JButton("4");
		JButton button_5 = new JButton("5");
		JButton button_6 = new JButton("6");
		JButton button_7 = new JButton("7");
		JButton button_8 = new JButton("8");
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = button.getText();
				
				if(button_1.getText().equals(""))
				{
					button.setText(button_1.getText());
					button_1.setText(temp);
				}
				else if(button_3.getText().equals(""))
				{
					button.setText(button_3.getText());
					button_3.setText(temp);
				}
			}
		});
		button.setFont(new Font("Arial", Font.BOLD, 50));
		button.setBounds(736, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button);
		button.setBackground(Color.WHITE);
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_1.getText();
				
				if(button.getText().equals(""))
				{
					button_1.setText(button.getText());
					button.setText(temp);
				}
				else if(button_2.getText().equals(""))
				{
					button_1.setText(button_2.getText());
					button_2.setText(temp);
				}
				else if(button_4.getText().equals(""))
				{
					button_1.setText(button_4.getText());
					button_4.setText(temp);
				}
			}
		});
		button_1.setFont(new Font("Arial", Font.BOLD, 50));
		button_1.setBounds(848, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_1);
		button_1.setBackground(Color.WHITE);
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = button_2.getText();
				
				if(button_1.getText().equals(""))
				{
					button_2.setText(button_1.getText());
					button_1.setText(temp);
				}
				else if(button_5.getText().equals(""))
				{
					button_2.setText(button_5.getText());
					button_5.setText(temp);
				}
			}
		});
		button_2.setFont(new Font("Arial", Font.BOLD, 50));
		button_2.setBounds(960, 13, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_2);
		button_2.setBackground(Color.WHITE);
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_3.getText();
				
				if(button.getText().equals(""))
				{
					button_3.setText(button.getText());
					button.setText(temp);
				}
				else if(button_6.getText().equals(""))
				{
					button_3.setText(button_6.getText());
					button_6.setText(temp);
				}
				else if(button_4.getText().equals(""))
				{
					button_3.setText(button_4.getText());
					button_4.setText(temp);
				}
			}
		});
		button_3.setFont(new Font("Arial", Font.BOLD, 50));
		button_3.setBounds(736, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_3);
		button_3.setBackground(Color.WHITE);
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_4.getText();
				
				if(button_1.getText().equals(""))
				{
					button_4.setText(button_1.getText());
					button_1.setText(temp);
				}
				else if(button_3.getText().equals(""))
				{
					button_4.setText(button_3.getText());
					button_3.setText(temp);
				}
				else if(button_5.getText().equals(""))
				{
					button_4.setText(button_5.getText());
					button_5.setText(temp);
				}
				else if(button_7.getText().equals(""))
				{
					button_4.setText(button_7.getText());
					button_7.setText(temp);
				}
			}
		});
		button_4.setFont(new Font("Arial", Font.BOLD, 50));
		button_4.setBounds(848, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_4);
		button_4.setBackground(Color.WHITE);
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_5.getText();
				
				if(button_8.getText().equals(""))
				{
					button_5.setText(button_8.getText());
					button_8.setText(temp);
				}
				else if(button_2.getText().equals(""))
				{
					button_5.setText(button_2.getText());
					button_2.setText(temp);
				}
				else if(button_4.getText().equals(""))
				{
					button_5.setText(button_4.getText());
					button_4.setText(temp);
				}
			}
		});
		button_5.setFont(new Font("Arial", Font.BOLD, 50));
		button_5.setBounds(960, 126, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_5);
		button_5.setBackground(Color.WHITE);
		
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_6.getText();
				
				if(button_3.getText().equals(""))
				{
					button_6.setText(button_3.getText());
					button_3.setText(temp);
				}
				else if(button_7.getText().equals(""))
				{
					button_6.setText(button_7.getText());
					button_7.setText(temp);
				}
			}
		});
		button_6.setFont(new Font("Arial", Font.BOLD, 50));
		button_6.setBounds(736, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_6);
		button_6.setBackground(Color.WHITE);
		
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String temp = button_7.getText();
				
				if(button_8.getText().equals(""))
				{
					button_7.setText(button_8.getText());
					button_8.setText(temp);
				}
				else if(button_6.getText().equals(""))
				{
					button_7.setText(button_6.getText());
					button_6.setText(temp);
				}
				else if(button_4.getText().equals(""))
				{
					button_7.setText(button_4.getText());
					button_4.setText(temp);
				}
			}
		});
		button_7.setFont(new Font("Arial", Font.BOLD, 50));
		button_7.setBounds(848, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_7);
		button_7.setBackground(Color.WHITE);
		
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String temp = button_8.getText();
				
				if(button_5.getText().equals(""))
				{
					button_8.setText(button_5.getText());
					button_5.setText(temp);
				}
				else if(button_7.getText().equals(""))
				{
					button_8.setText(button_7.getText());
					button_7.setText(temp);
				}
			}
		});
		button_8.setFont(new Font("Arial", Font.BOLD, 50));
		button_8.setBounds(960, 239, 100, 100);
		frmpuzzleSolveIt.getContentPane().add(button_8);
		button_8.setBackground(Color.WHITE);
		
		
		JButton btn_start = new JButton("Start");
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dot_bfs.setEnabled(false);
				dot_astar.setEnabled(false);
				
				far = 0;
				
				Scanner scan = new Scanner(System.in);
				
				try
				{
					node = Integer.valueOf((in_node.getText()));
				}
				catch(Exception e)
				{
					in_node.setText("1000");
				}
				if(node < 1000)
				{
					node = 1000;
					in_node.setText("1000");
				}
				
				mem = new Mem[node];
				
				String[] a_pre = {button.getText() , button_1.getText() , button_2.getText() ,
						 		  button_3.getText() , button_4.getText() , button_5.getText() ,
						 		  button_6.getText() , button_7.getText() , button_8.getText() };
				int[] answer = new int[a_pre.length];
				for(int i=0; i<answer.length; i++)
				{
					if(a_pre[i].equals(""))
					{
						answer[i] = 0;
					}
					else
						answer[i] = Integer.valueOf(a_pre[i]);
				}
				
				String[] q_pre = 	{btn_0.getText() , btn_1.getText() , btn_2.getText() ,
									 btn_3.getText() , btn_4.getText() , btn_5.getText() ,
									 btn_6.getText() , btn_7.getText() , btn_8.getText() };
				int[] quest = new int[q_pre.length];
				for(int i=0; i<quest.length; i++)
				{
					if(q_pre[i].equals(""))
					{
						quest[i] = 0;
					}
					else
						quest[i] = Integer.valueOf(q_pre[i]);
				}
				
				int lst_mem = 0;
				mem[lst_mem] = new Mem();
				mem[lst_mem].save(quest, 0);
				int to_sprd = lst_mem;
				
				while(!chk.isCorrect(answer, mem[to_sprd].getMem()) && lst_mem < node)
				{
					for(int j=0; j<4; j++)
					{
						int[] m_temp = new int[mem[to_sprd].getMem().length];
						for(int i=0; i<m_temp.length; i++)
						{
							m_temp[i] = mem[to_sprd].getMem()[i];
						}
						int[] q_temp = sw.spread(m_temp, j);
						
						if(!cmpr.isSame(q_temp, mem, lst_mem))
						{
							lst_mem++;
							try
							{
								mem[lst_mem] = new Mem();
								sprd_node.setText(""+(lst_mem+1));
							}
							catch(Exception e)
							{
								System.out.println(e.toString());
								break;
							}
							mem[lst_mem].save(q_temp, to_sprd);
						}
					}
					mem[to_sprd].setOld(true);
					
					if(dot_bfs.isSelected())
						to_sprd = chk.choosePath_BFS(answer, mem, lst_mem);
					else
						to_sprd = chk.choosePath_Astar(answer, mem, lst_mem);
				}
				
				dot_bfs.setEnabled(true);
				dot_astar.setEnabled(true);
				
				int c_node = to_sprd;
				int c = 0;
				String str = "";
				
				while(to_sprd != 0)
				{	
					nxt[far] = to_sprd;
					
					str += "\n" + mem[to_sprd].toString() + "\n";
					to_sprd = mem[to_sprd].getCameFrom();
					
					far++;
				}
				str += "\n" + mem[0].toString() + "\n";
				
				if(chk.isCorrect(answer, mem[c_node].getMem()))	
				{
					str += "\nCorrect at\nNode : " + c_node + "\n" + "Moves :" + far + "\n";
					finish_dis.setText("SUCCESS");
					finish_dis.setForeground(Color.green);
				}
				else
				{
					str += "\nFail\n";
					finish_dis.setText("FAIL");
					finish_dis.setForeground(Color.RED);
				}
				
				text_dis.setText(str);
				
				btn_roll.setEnabled(true);
			}
		});
		btn_start.setForeground(Color.BLACK);
		btn_start.setFont(new Font("Arial", Font.BOLD, 50));
		btn_start.setBounds(348-180, 352, 182, 69);
		frmpuzzleSolveIt.getContentPane().add(btn_start);
		btn_start.setBackground(Color.WHITE);
		
		
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				far = 0;
				node = 1000;
				in_node.setText("1000");
				sprd_node.setText("0");
				
				text_dis.setText(">>> Goal >>> Click BFS or A* First");
				
				btn_0.setText("");
				btn_1.setText("1");
				btn_2.setText("2");
				btn_3.setText("3");
				btn_4.setText("4");
				btn_5.setText("5");
				btn_6.setText("6");
				btn_7.setText("7");
				btn_8.setText("8");
			}
		});
		btn_reset.setForeground(Color.RED);
		btn_reset.setFont(new Font("Arial", Font.BOLD, 50));
		btn_reset.setBounds(542-180, 352, 182, 69);
		frmpuzzleSolveIt.getContentPane().add(btn_reset);
		btn_reset.setBackground(Color.WHITE);
		
		
		finish_dis.setFont(new Font("Arial", Font.BOLD, 40));
		finish_dis.setHorizontalAlignment(SwingConstants.CENTER);
		finish_dis.setBounds(12, 352, 324, 69);
		frmpuzzleSolveIt.getContentPane().add(finish_dis);
		
		
		btn_roll.setEnabled(false);
		btn_roll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				far--;
				
				if(far >= 0)
				{
					int[] m_temp = mem[nxt[far]].getMem();
					
					btn_0.setText(""+ ((m_temp[0]==0)? "" : m_temp[0]));
					btn_1.setText(""+ ((m_temp[1]==0)? "" : m_temp[1]));
					btn_2.setText(""+ ((m_temp[2]==0)? "" : m_temp[2]));
					btn_3.setText(""+ ((m_temp[3]==0)? "" : m_temp[3]));
					btn_4.setText(""+ ((m_temp[4]==0)? "" : m_temp[4]));
					btn_5.setText(""+ ((m_temp[5]==0)? "" : m_temp[5]));
					btn_6.setText(""+ ((m_temp[6]==0)? "" : m_temp[6]));
					btn_7.setText(""+ ((m_temp[7]==0)? "" : m_temp[7]));
					btn_8.setText(""+ ((m_temp[8]==0)? "" : m_temp[8]));
				}
			}
		});
		btn_roll.setHorizontalAlignment(SwingConstants.LEFT);
		btn_roll.setForeground(Color.BLACK);
		btn_roll.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 45));
		btn_roll.setBounds(368, 289, 150, 50);
		frmpuzzleSolveIt.getContentPane().add(btn_roll);
		btn_roll.setBackground(Color.WHITE);
	}
}
