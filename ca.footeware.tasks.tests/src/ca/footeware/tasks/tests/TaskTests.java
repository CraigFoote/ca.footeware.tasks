package ca.footeware.tasks.tests;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TaskTests {

	private static SWTWorkbenchBot bot;
	@Inject
	private static IEclipseContext context;

	@BeforeAll
	public static void beforeClass() throws Exception {
		bot = new SWTWorkbenchBot(context);
//		bot.partByTitle("Welcome").close();
	}

	@Test
	public void canCreateANewJavaProject() throws Exception {
		bot.menu("File").menu("New").menu("Project...").click();

		SWTBotShell shell = bot.shell("New Project");
		shell.activate();
		bot.tree().expandNode("Java").select("Java Project");
		bot.button("Next >").click();

		bot.textWithLabel("Project name:").setText("MyFirstProject");

		bot.button("Finish").click();
		// FIXME: assert that the project is actually created, for later
	}

	@AfterAll
	public static void sleep() {
		bot.sleep(2000);
	}

}