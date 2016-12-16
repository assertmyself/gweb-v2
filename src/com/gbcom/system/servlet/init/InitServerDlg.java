package com.gbcom.system.servlet.init;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import p1.DOGException;

import com.gbcom.omc.validate.Author;
import com.gbcom.omc.validate.OmcVerifier;

/**
 * 客户端提醒没有插入dog
 * 
 * @author duanxiongwen
 */
public final class InitServerDlg {
	
	private static Logger log = Logger.getLogger(InitServerDlg.class);

	/**
	 * 单例
	 */
	private static InitServerDlg instance;

	/**
	 * 提示框
	 */
	private static JOptionPane optionPane;

	/**
	 * 显示对话框
	 */
	private JDialog createDialog = null;

	/**
	 * 显示内容
	 */
	private static String showMessage = "";

	/**
	 * @return the showMessage
	 */
	public static String getShowMessage() {
		return showMessage;
	}

	/**
	 * 是否合法
	 */
	private static boolean isValidate = true;

	/**
	 * @return the isValidate
	 */
	public static boolean isValidate() {
		return isValidate;
	}

	/**
	 * @param isValidate
	 *            the isValidate to set
	 */
	public static void setValidate(boolean isValidate) {
		InitServerDlg.isValidate = isValidate;
	}

	/**
	 * 构造方法
	 */
	private InitServerDlg() {
		optionPane = new JOptionPane("xx", JOptionPane.QUESTION_MESSAGE,
				JOptionPane.CLOSED_OPTION);
		createDialog = optionPane.createDialog("初始化服务提示框");
	}

	/**
	 * 获取单例
	 * 
	 * @return DogServerDlg
	 */
	public static InitServerDlg getInstance() {
		if (null == instance) {
			instance = new InitServerDlg();
		}
		return instance;
	}

	/**
	 * 显示提示框
	 * 
	 * @param message
	 *            显示内容
	 */
	public synchronized void showDailog(final String message) {
		final SwingWorker<?, ?> worker = new SwingWorker<Object, Object>() {

			@Override
			protected Object doInBackground() throws Exception {
				return null;
			}

			@Override
			protected void done() {
				super.done();
				if (message.equalsIgnoreCase("success")) {
					createDialog.setVisible(false);
				} else {
					if (createDialog.isVisible()) {
						if (!showMessage.equals(message)) {
							showMessage = message;
							optionPane.setMessage(message);
							optionPane.repaint();
							createDialog.repaint();
						}
					} else {
						optionPane.setMessage(message);
						createDialog.setContentPane(optionPane);
						createDialog.pack();
						createDialog.setLocationRelativeTo(createDialog);
						createDialog.setVisible(true);
					}
				}
				try {
					sendMessage(message);
				} catch (Exception e) {
					log.error("Exception：",e);
				}
			}

		};
		worker.execute();

	}

	/**
	 * 发送消息
	 * 
	 * 
	 * @param message
	 *            消息
	 */
	private void sendMessage(String message) {

	}

	/**
	 * 测试方法
	 * 
	 * @param args
	 *            args
	 */
	public static void main(String[] args) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				int cpenum = 100;
				long result = OmcVerifier.validate();
				if (result != 0L) {
					InitServerDlg.getInstance().showDailog(
							DOGException.getErrorMsg((int) result, 0));
					InitServerDlg.setValidate(false);

				} else {
					String cpeAuthorNum = OmcVerifier.getAuthorData(Author.AC);
					if (cpenum > Integer.valueOf(cpeAuthorNum)) {
						InitServerDlg.getInstance().showDailog(
								"您的授权项数目为" + cpeAuthorNum + "小于您的实际使用值"
										+ cpenum);
						InitServerDlg.setValidate(false);
					} else {
						InitServerDlg.getInstance().showDailog("success");
						InitServerDlg.setValidate(true);
					}
				}

			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 10000, 20000);
	}

}
