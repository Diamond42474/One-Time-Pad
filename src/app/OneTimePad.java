package app;

import dialogue.Dialogue;
import execute.Encry_Decry;

public class OneTimePad {

	public static void main(String[] args) {
		System.out.println(	"	  ______________        ____________________         ____________\n"
				+ 			"	//              \\\\     |____________________|        ||        \\\\\n"
				+			"	||              ||              |  |                 ||         ||\n"
				+ 			"	||              ||              |  |                 ||         ||\n"
				+ 			"	||              ||              |  |                 ||_________//\n"
				+ 			"	||              ||              |  |                 ||\n"
				+ 			"	||              ||              |  |                 ||\n"
				+ 			"	||              ||              |  |                 ||\n"
				+ 			"	||              ||              |  |                 ||\n"
				+ 			"	\\\\______________//              |__|                 ||\n"
				+ 			"\n	By: Diamond42474\n\n\n");
		 Encry_Decry.Setup(); 
		 Dialogue.Start();
	}
}
