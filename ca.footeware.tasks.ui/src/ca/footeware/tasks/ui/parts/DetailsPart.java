<<<<<<< HEAD
package ca.footeware.tasks.ui.parts;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Calendar;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

public class DetailsPart {

	private String id;
	private Text titleText;
	private Text descriptionText;
	private DateTime dueDateTime;
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
		titleText.addModifyListener(e -> dirty.setDirty(true));

		Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText("Description:");
		GridDataFactory.defaultsFor(descriptionLabel).align(SWT.RIGHT, SWT.TOP).applyTo(descriptionLabel);

		descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.defaultsFor(descriptionText).grab(true, true).applyTo(descriptionText);
		descriptionText.addModifyListener(e -> dirty.setDirty(true));

		Label dueLabel = new Label(parent, SWT.NONE);
		dueLabel.setText("Due:");
		GridDataFactory.defaultsFor(dueLabel).align(SWT.RIGHT, SWT.CENTER).applyTo(dueLabel);

		dueDateTime = new DateTime(parent, SWT.DROP_DOWN);
		dueDateTime.addSelectionListener(widgetSelectedAdapter(e -> dirty.setDirty(true)));

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

		selectionService.addSelectionListener((part, selection) -> {
			if (!(selection instanceof IStructuredSelection)) {
				return;
			}
			IStructuredSelection sselection = (IStructuredSelection) selection;
			if (sselection.isEmpty()) {
				return;
			}
			Task task = (Task) sselection.getFirstElement();
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
				Calendar cal = Calendar.getInstance(Locale.CANADA);
				cal.setTime(task.getDue());
				dueDateTime.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
			}
			id = task.getId();
			dirty.setDirty(false);
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
		Calendar cal = Calendar.getInstance(Locale.CANADA);
		if (id == null) {
			/*
			 * Null ID means the Task is being created, not updated. It'll get an ID on
			 * instantiation.
			 */
			int year = dueDateTime.getYear();
			int month = dueDateTime.getMonth();
			int day = dueDateTime.getDay();
			cal.set(year, month, day);
			taskService.createTask(titleText.getText().trim(), descriptionText.getText().trim(), cal.getTime(),
					completedButton.getSelection());
		} else {
			// have ID will update
			taskService.updateTask(id, titleText.getText().trim(), descriptionText.getText().trim(), cal.getTime(),
					completedButton.getSelection());
		}
		dirty.setDirty(false);
		MPart mPart = partService.findPart("ca.footeware.tasks.ui.part.tasks");
		TasksPart tasksPart = (TasksPart) mPart.getObject();
		if (tasksPart != null) {
			tasksPart.refresh();
		}
	}

	public void clear() {
		if (titleText != null && !titleText.isDisposed()) {
			titleText.setText("");
		}
		if (descriptionText != null && !descriptionText.isDisposed()) {
			descriptionText.setText("");
		}
		if (completedButton != null && !completedButton.isDisposed()) {
			completedButton.setSelection(false);
		}
		id = null;
=======

package ca.footeware.tasks.ui.parts;

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Calendar;
import java.util.Locale;

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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ca.footeware.tasks.service.ITaskService;
import ca.footeware.tasks.service.models.Task;

public class DetailsPart {

	private String id;
	private Text titleText;
	private Text descriptionText;
	private DateTime dueDateTime;
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
		titleText.addModifyListener(e -> dirty.setDirty(true));

		Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText("Description:");
		GridDataFactory.defaultsFor(descriptionLabel).align(SWT.RIGHT, SWT.TOP).applyTo(descriptionLabel);

		descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		GridDataFactory.defaultsFor(descriptionText).grab(true, true).applyTo(descriptionText);
		descriptionText.addModifyListener(e -> dirty.setDirty(true));

		Label dueLabel = new Label(parent, SWT.NONE);
		dueLabel.setText("Due:");
		GridDataFactory.defaultsFor(dueLabel).align(SWT.RIGHT, SWT.CENTER).applyTo(dueLabel);

		dueDateTime = new DateTime(parent, SWT.DROP_DOWN);
		dueDateTime.addSelectionListener(widgetSelectedAdapter(e -> dirty.setDirty(true)));

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
					Calendar cal = Calendar.getInstance(Locale.CANADA);
					cal.setTime(task.getDue());
					dueDateTime.setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
							cal.get(Calendar.DAY_OF_MONTH));
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
		Calendar cal = Calendar.getInstance(Locale.CANADA);
		if (id == null) {
			int year = dueDateTime.getYear();
			int month = dueDateTime.getMonth();
			int day = dueDateTime.getDay();
			cal.set(year, month, day);

			taskService.createTask(titleText.getText().trim(), descriptionText.getText().trim(), cal.getTime(),
					completedButton.getSelection());
		} else {
			taskService.saveTask(id, titleText.getText().trim(), descriptionText.getText().trim(),
					cal.getTime(), completedButton.getSelection());
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
		if (completedButton != null && !completedButton.isDisposed()) {
			completedButton.setSelection(false);
		}
>>>>>>> branch 'master' of https://github.com/CraigFoote/ca.footeware.tasks.git
		dirty.setDirty(false);
	}

}