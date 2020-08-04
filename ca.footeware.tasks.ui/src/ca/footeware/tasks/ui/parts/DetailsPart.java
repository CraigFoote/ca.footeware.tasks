
package ca.footeware.tasks.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.e4.ui.workbench.modeling.ISelectionListener;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

public class DetailsPart {

	private String id;
	private Text titleText;
	private Text descriptionText;
	private CDateTime dueDateTime;
	private Button completedButton;
	@Inject
	private ESelectionService selectionService;
	@Inject
	private MDirtyable dirty;
	@Inject
	private ITaskService taskService;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		Label nameLabel = new Label(parent, SWT.NONE);
		nameLabel.setText("Title:");
		GridDataFactory.defaultsFor(nameLabel).align(SWT.RIGHT, SWT.CENTER).applyTo(nameLabel);

		titleText = new Text(parent, SWT.BORDER);
		GridDataFactory.defaultsFor(titleText).grab(true, false).applyTo(titleText);
		titleText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});

		Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText("Description:");
		GridDataFactory.defaultsFor(descriptionLabel).align(SWT.RIGHT, SWT.TOP).applyTo(descriptionLabel);

		descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.defaultsFor(descriptionText).grab(true, true).applyTo(descriptionText);
		descriptionText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});

		Label dueLabel = new Label(parent, SWT.NONE);
		dueLabel.setText("Due:");
		GridDataFactory.defaultsFor(dueLabel).align(SWT.RIGHT, SWT.CENTER).applyTo(dueLabel);

		dueDateTime = new CDateTime(parent, CDT.BORDER | CDT.COMPACT | CDT.DROP_DOWN | CDT.DATE_LONG | CDT.TIME_MEDIUM);
		GridDataFactory.defaultsFor(dueDateTime).grab(true, false).applyTo(dueDateTime);
		dueDateTime.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});

		Label completedLabel = new Label(parent, SWT.NONE);
		completedLabel.setText("Completed:");
		GridDataFactory.defaultsFor(completedLabel).align(SWT.RIGHT, SWT.CENTER).applyTo(completedLabel);

		completedButton = new Button(parent, SWT.CHECK);
		GridDataFactory.defaultsFor(completedButton).grab(true, false).applyTo(completedButton);
		completedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dirty.setDirty(true);
			}
		});

		selectionService.addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(MPart part, Object selection) {
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection sselection = (IStructuredSelection) selection;
					if (sselection.isEmpty()) {
						return;
					}
				}
				Task task = (Task) ((IStructuredSelection) selection).getFirstElement();
				if (titleText != null && !titleText.isDisposed()) {
					titleText.setText(task.getTitle());
				}
				if (descriptionText != null && !descriptionText.isDisposed()) {
					descriptionText.setText(task.getDescription());
				}
				if (completedButton != null && !completedButton.isDisposed()) {
					completedButton.setSelection(task.isCompleted());
				}
				if (dueDateTime != null && !dueDateTime.isDisposed()) {
					dueDateTime.setSelection(task.getDue());
				}
				id = task.getId();
				dirty.setDirty(false);
			}
		});
	}

	@Focus
	public void setFocus() {
		if (titleText != null && !titleText.isDisposed()) {
			titleText.setFocus();
		}
	}

	@Persist
	public void save(EPartService partService) {
		if (id == null) {
			taskService.createTask(titleText.getText().trim(), descriptionText.getText().trim(),
					dueDateTime.getSelection(), completedButton.getSelection());
		} else {
			taskService.saveTask(id, titleText.getText().trim(), descriptionText.getText().trim(),
					dueDateTime.getSelection(), completedButton.getSelection());
		}
		dirty.setDirty(false);
		MPart mPart = partService.findPart("ca.footeware.tasks.ui.part.tasks");
		TasksPart tasksPart = (TasksPart) mPart.getObject();
		tasksPart.refresh();
	}

	public void clear() {
		if (titleText != null && !titleText.isDisposed()) {
			titleText.setText("");
		}
		if (descriptionText != null && !descriptionText.isDisposed()) {
			descriptionText.setText("");
		}
		if (dueDateTime != null && !dueDateTime.isDisposed()) {
			dueDateTime.setSelection(null);
		}
		if (completedButton != null && !completedButton.isDisposed()) {
			completedButton.setSelection(false);
		}
		dirty.setDirty(false);
	}

}