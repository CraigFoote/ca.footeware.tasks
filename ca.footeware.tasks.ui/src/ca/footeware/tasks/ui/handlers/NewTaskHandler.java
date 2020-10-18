 
package ca.footeware.tasks.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import ca.footeware.tasks.ui.parts.DetailsPart;

public class NewTaskHandler {
	
	@Execute
	public void execute(EPartService partService) {
		MPart activePart = partService.getActivePart();
		DetailsPart part = (DetailsPart) activePart.getObject();
		part.clear();
	}
		
}