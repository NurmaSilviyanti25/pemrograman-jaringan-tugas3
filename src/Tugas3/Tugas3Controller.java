/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author User
 */
public class Tugas3Controller {
    private Tugas3 view;
    private List<Integer> list = new ArrayList<>();
    
    public Tugas3Controller(Tugas3 view){
        this.view = view;
        
            this.view.getBtnBaca().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proses();
            }
        }) ;
        
        this.view.getBtnSimpan().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        
    }
    private JTextPane txtPane;
    
    private void save() {
        JFileChooser loadFile = view.getLoadFile();
        if(JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)){
            BufferedWriter writer = null;
            try{
                String contents = view.getTxtPane().getText();
                if(contents != null && !contents.isEmpty()) {
                    writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                    writer.write(contents);
                }
            } catch(FileNotFoundException ex){
                Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);            
            } catch(IOException ex) {
                Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(writer != null) {
                    try{
                        writer.flush();
                        writer.close();
                        view.getTxtPane().setText("");
                        //JOptionPane.showMessageDialog(null, "Berhasil Disimpan");
                    }catch(IOException ex){
                        Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                }
            }
        }
    }
    
    private void proses() {
        
        JFileChooser loadFile = view.getLoadFile();
        StyledDocument doc = view.getTxtPane().getStyledDocument();
        if(JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)){
            BufferedReader reader = null;
            try{
                reader = new BufferedReader(new FileReader(loadFile.getSelectedFile()));
                String data = null;
                doc.insertString(0, "", null);
                while((data = reader.readLine()) != null){
                    doc.insertString(doc.getLength(), data, null);
                    doc.insertString(doc.getLength(), "\n", null);
                }
            }catch (FileNotFoundException ex){
                Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }catch(IOException | BadLocationException ex){
                Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                if(reader != null){
                    try{
                    reader.close();
                }catch(IOException ex){
                        Logger.getLogger(Tugas3Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
            }
            
        }
    }
  }
}

    