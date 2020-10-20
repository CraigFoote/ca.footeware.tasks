package ca.footeware.tasks.tests;

import java.util.Date;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swtbot.e4.finder.widgets.SWTBotView;
import org.eclipse.swtbot.e4.finder.widgets.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class TaskTest {

	private SWTWorkbenchBot bot;
	private IEclipseContext context;
	private static final String TASKS = "Tasks";
	private static final String DETAILS = "Details";

	@BeforeEach
	public void beforeEach() {
		BundleContext bundleContext = FrameworkUtil.getBundle(TaskTest.class).getBundleContext();
		IEclipseContext serviceContext = EclipseContextFactory.getServiceContext(bundleContext);
		context = serviceContext.get(IWorkbench.class).getApplication().getContext();
		bot = new SWTWorkbenchBot(context);
	}

	@Test
	public void testOpenViews() throws Exception {
		Assertions.assertTrue(bot.partByTitle(TASKS).getPart().isVisible());
		Assertions.assertTrue(bot.partByTitle(DETAILS).getPart().isVisible());
		bot.partByTitle(TASKS).close();
		bot.partByTitle(DETAILS).close();

		bot.menu("Window").menu("Show View").menu(TASKS).click();
		Assertions.assertTrue(bot.partByTitle(TASKS).getPart().isVisible());

		bot.menu("Window").menu("Show View").menu(DETAILS).click();
		Assertions.assertTrue(bot.partByTitle(DETAILS).getPart().isVisible());
	}

	@Test
	public void testCreateTask() {
		SWTBotView part = bot.partByTitle(DETAILS);
		part.show();
		Assertions.assertTrue(part.getPart().isVisible());
		part.toolbarButton("New Task").click();

		bot.textWithLabel("Title:").setText("testTitle");
		bot.textWithLabel("Description:").setText("testDescription");
		Date date = new Date();
		bot.dateTime().setDate(date);

		part.toolbarButton("Save Task").click();

		part = bot.partByTitle(TASKS);
		part.show();
		SWTBotList botList = bot.list();
		Assertions.assertEquals(1, botList.itemCount());
		String taskName = botList.getItems()[0];
		Assertions.assertEquals("testTitle", taskName);
	}

}