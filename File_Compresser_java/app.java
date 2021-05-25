import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * 
 * @author boxiang lin
 *
 */
public class app implements ActionListener {
	
	HuffmanAlgorithms algorithms;
	
	public app() {
		algorithms = new HuffmanAlgorithms();
		JFrame frame = new JFrame("File Compressor");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setSize(350,250);
		frame.setLocation(500,250);
		frame.setResizable(false); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel encode =  new JPanel();
		frame.add(encode);
		JButton srcFile = new JButton("Load (Compress/Encode)");
		encode.add(srcFile);
		encode.setBounds(0, 0, 350, 105);
		encode.setBackground(new Color(128,128,128));
		srcFile.setActionCommand("zip");
		srcFile.addActionListener(this);
 
		 
		
		JPanel decode = new JPanel();
		frame.add(decode);
		JButton zipFile = new JButton("Load (Restore/Decode");
		decode.add(zipFile);
		decode.setBounds(0, 105, 350, 125);
		decode.setBackground(new Color(64,203,255));
		zipFile.setActionCommand("unzip");
		zipFile.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser(".");
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	 
		String address, dstAddress;
	 
		
		
		if(e.getActionCommand().equals("zip")) {
			
			int status = chooser.showOpenDialog(null);
			if(status == JFileChooser.APPROVE_OPTION) {
				address = chooser.getSelectedFile().getAbsolutePath();
				String name = chooser.getSelectedFile().getName();
				name = name.substring(0,name.lastIndexOf("."));
				dstAddress = chooser.getCurrentDirectory().getAbsolutePath()+"\\"+name+".zip";
				algorithms.zipFile(address, dstAddress);
				JOptionPane.showMessageDialog(null, "File has been compressed/encode to "+name+".zip");   
			}
			
		}else if(e.getActionCommand().equals("unzip")) {
			
			chooser.setFileFilter(new FileNameExtensionFilter("zip","zip"));
			int status = chooser.showOpenDialog(null);

			if(status == JFileChooser.APPROVE_OPTION) {
				address = chooser.getSelectedFile().getAbsolutePath();
				String fileName = JOptionPane.showInputDialog(null,"Please Enter the fileName.fileType: \n","Enter file name",JOptionPane.PLAIN_MESSAGE); 
				dstAddress = chooser.getCurrentDirectory().getAbsolutePath()+"\\"+fileName;
				
				if(fileName != null) {
					
					algorithms.unZipFile(address, dstAddress);
					JOptionPane.showMessageDialog(null, "File has been restored/decoded to " + fileName);  
				} 
				 
			}
		}
			
	}
		
		
	public static void main(String[] args) {
		new app();

	}

}
