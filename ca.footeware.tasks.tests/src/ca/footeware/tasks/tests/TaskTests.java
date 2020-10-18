package ca.footeware.tasks.tests;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class TaskTests {

	private static SWTWorkbenchBot bot;

	@BeforeAll
	public static void beforeClass() {
		BundleContext bundleContext = FrameworkUtil.getBundle(TaskTests.class).getBundleContext();
		IEclipseContext serviceContext = EclipseContextFactory.getServiceContext(bundleContext);
		IEclipseContext context = serviceContext.get(IWorkbench.class).getApplication().getContext();
		bot = new SWTWorkbenchBot(context);
	}

	@Test
	public void canOpenViews() throws Exception {
		Assertions.assertTrue(bot.partByTitle("Tasks").getPart().isVisible());
		Assertions.assertTrue(bot.partByTitle("Details").getPart().isVisible());
		bot.partByTitle("Tasks").close();
		bot.partByTitle("Details").close();

		bot.menu("Window").menu("Show View").menu("Tasks").click();
		Assertions.assertTrue(bot.partByTitle("Tasks").getPart().isVisible());

		bot.menu("Window").menu("Show View").menu("Details").click();
		Assertions.assertTrue(bot.partByTitle("Details").getPart().isVisible());

//		
//		SWTBotShell shell = bot.shell("New Project");
//		shell.activate();
//		bot.tree().expandNode("Java").select("Java Project");
//		bot.button("Next >").click();
//
//		bot.textWithLabel("Project name:").setText("MyFirstProject");
//
//		bot.button("Finish").click();
	}

	@AfterAll
	public static void sleep() {
		bot.sleep(2000);
	}

}