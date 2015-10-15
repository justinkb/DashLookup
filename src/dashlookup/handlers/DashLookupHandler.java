package dashlookup.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class DashLookupHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public DashLookupHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		String selectedText = getSelectedText(window);
		
		if (selectedText.isEmpty() && window.getActivePage() != null) {
			selectedText = getTextFromSelection(window.getActivePage().getSelection());
		}
		
		if (!selectedText.isEmpty()) {
			Program.launch("dash://" + selectedText);
		}
		
		return null;
	}
	
	private String getTextFromSelection(ISelection selection) {
		String selectedText = "";
		if (selection instanceof ITextSelection) {
			selectedText = ((ITextSelection) selection).getText();
		}
		
		return selectedText;
	}

	private String getSelectedText(IWorkbenchWindow window) {
		String selectedText = "";
		final ISelectionService service = window.getSelectionService();

		if (service != null) {
			selectedText = getTextFromSelection(service.getSelection());
		}
		
		return selectedText;
	}

}
