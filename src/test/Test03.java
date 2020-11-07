package test;

import javax.swing.JOptionPane;

import dao.BookDAO_Mariadb;

public class Test03 {

	public static void main(String[] args) {
		
		
		
		Command insert = () -> {
			System.out.println("InsertCommand exec ..");
		};

		Command delete = () -> {
			System.out.println("DeleteCommand exec ..");
		};

		Command update = new UpdateCommand();

		String cmd = JOptionPane.showInputDialog("1.insert , 2.delete , 3.update , 4.quit");

		switch (cmd) {
		case "1":
			insert.exec();
			break;
		case "2":
			delete.exec();
			break;
		case "3":
			update.exec();
			break;
		case "4":
			System.out.println("Quit");
			break;
		}
	}
}

@FunctionalInterface
interface Command {
	public abstract void exec();
}

class DeleteCommand implements Command {
	public void exec() {
		System.out.println("DeleteCommand exec ..");
	}
}


class UpdateCommand implements Command {
	public void exec() {
		System.out.println("UpdateCommand exec ..");
	}
}
