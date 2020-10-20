
package ca.footeware.tasks.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ArrayContentProvider;
<<<<<<< HEAD
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

public class TasksPart {

	@Inject
	private ITaskService taskService;
	@Inject
	private ESelectionService selectionService;
	private ListViewer taskList;

	@PostConstruct
	public void postConstruct(Composite parent) {
		taskList = new ListViewer(parent, SWT.BORDER | SWT.SCROLL_LINE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		taskList.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Task) element).getTitle();
			}
		});
		taskList.setContentProvider(new ArrayContentProvider() {
			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object inputElement) {
				return ((java.util.List<Task>) inputElement).toArray();
			}
		});
		taskList.addSelectionChangedListener(event -> selectionService.setSelection(event.getSelection()));
=======
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

public class TasksPart {

	@Inject
	private ITaskService taskService;
	@Inject
	private ESelectionService selectionService;
	private ListViewer taskList;

	@PostConstruct
	public void postConstruct(Composite parent) {
		taskList = new ListViewer(parent, SWT.BORDER | SWT.SCROLL_LINE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE);
		taskList.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return ((Task) element).getTitle();
			}
		});
		taskList.setContentProvider(new ArrayContentProvider() {
			@SuppressWarnings("unchecked")
			@Override
			public Object[] getElements(Object inputElement) {
				return ((java.util.List<Task>) inputElement).toArray();
			}
		});
		taskList.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectionService.setSelection(event.getSelection());
			}
		});
>>>>>>> branch 'master' of https://github.com/CraigFoote/ca.footeware.tasks.git
		taskList.setInput(taskService.getTasks());
		taskList.setSelection(StructuredSelection.EMPTY);
	}

	@Focus
	public void onFocus() {
		if (taskList != null && taskList.getControl() != null && !taskList.getControl().isDisposed()) {
			taskList.getControl().setFocus();
		}
	}

	public void refresh() {
		if (taskList != null && taskList.getControl() != null && !taskList.getControl().isDisposed()) {
			taskList.refresh();
		}
	}

}