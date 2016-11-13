package jpe.jb.xcopy;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;

public class XCopyPlugin extends AbstractProjectComponent {

    private Project project;
    private XCopyListener listener = null;

    public XCopyPlugin(Project project) {
        super(project);
        this.project = project;
    }

    public void initComponent() {
        listener = new XCopyListener();
        project.getMessageBus().connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, listener);
    }

    public void disposeComponent() {
    }

    @Override
    public String getComponentName() {
        return "XCopyProjectComponent";
    }

    @Override
    public void projectOpened() {
    }

    @Override
    public void projectClosed() {
    }
}