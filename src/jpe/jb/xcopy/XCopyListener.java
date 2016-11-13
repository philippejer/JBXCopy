package jpe.jb.xcopy;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.HashSet;

import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintStream;

class XCopyListener implements FileEditorManagerListener, MouseListener {

    private static String clipboard = "";
    private static HashSet<JTextComponent> registeredComponents = new HashSet<>();

    @Override
    public void fileOpened(FileEditorManager manager, VirtualFile file) {
    }

    @Override
    public void fileClosed(FileEditorManager manager, VirtualFile file) {
    }

    @Override
    public void selectionChanged(FileEditorManagerEvent event) {
        Editor editor = event.getManager().getSelectedTextEditor();
        if (editor == null)
            return;
        JTextComponent component = (JTextComponent) editor.getContentComponent();
        if (component == null)
            return;
        if (!registeredComponents.contains(component)) {
            component.addMouseListener(this);
            registeredComponents.add(component);
        }
    }

    synchronized static void setClipboard(String text) {
        clipboard = text;
    }

    synchronized static void pasteClipboard(Editor editor) {
        JTextComponent component = (JTextComponent) editor.getContentComponent();
        if (component == null)
            return;
        int position = component.getCaretPosition();
        String text = component.getText();
        text = text.substring(0, position) + clipboard + text.substring(position);
        component.setText(text);
        component.setCaretPosition(position + clipboard.length());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Would be better to use the caret event but the event is never received for some reason...
        if (e.getButton() == MouseEvent.BUTTON1) {
            JTextComponent textComponent = (JTextComponent) e.getSource();
            String selectedText = textComponent.getSelectedText();
            if ((selectedText != null) && !selectedText.isEmpty()) {
                setClipboard(selectedText);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
