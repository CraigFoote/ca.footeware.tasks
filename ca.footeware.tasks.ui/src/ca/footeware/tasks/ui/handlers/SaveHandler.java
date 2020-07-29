
package ca.footeware.tasks.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class SaveHandler {

	@Execute
	public void execute(EPartService partService) {
		MPart activePart = partService.getActivePart();
		partService.savePart(activePart, false);
	}

}