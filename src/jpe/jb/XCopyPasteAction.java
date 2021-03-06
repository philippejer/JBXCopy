package jpe.jb;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;

class XCopyPasteAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(CommonDataKeys.EDITOR_EVEN_IF_INACTIVE);
        if (editor == null)
            return;
        XCopyListener.pasteClipboard(editor);
    }
}
