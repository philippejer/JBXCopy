package jpe.jb.xcopy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;

class XCopyPasteAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(DataKeys.EDITOR_EVEN_IF_INACTIVE);
        if (editor == null)
            return;
        XCopyListener.pasteClipboard(editor);
    }
}
