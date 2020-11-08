package spaceshapes.views;


import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;

/**
 * Task2 extends the Task1 class such that a Task2 instance can both render a ShapeModel’s 
 * shape composition and respond to changes that occur in the ShapeModel. It's connected JTree  
 * component will update its display whenever the ShapeModel changes. 
 * 
 * @author Jia Qi
 *
 */
public class Task2 extends Task1 implements ShapeModelListener {

	private TreeModelEvent _tEvent;
	private List<Shape> _list;
	private Object[] array;
	
	public Task2(ShapeModel o ) {
		super(o);
	}
	
	public void update(ShapeModelEvent event) {
		
		ShapeModelEvent.EventType eventType = event.eventType();
		ShapeModel source = event.source();
		Shape parent = event.parent();

		int[] indices = {event.index()};
		Shape[] shapes = {event.operand()};
		
		if(event.parent() == null) {
			_list = event.operand().path();
		}
		else {
			_list = parent.path();
		}
		
		array = _list.toArray();
		
	    _tEvent = new TreeModelEvent(source, array, indices, shapes);
		
		if(eventType == ShapeModelEvent.EventType.ShapeAdded) {
			for (TreeModelListener lis: _treeListeners) {
				lis.treeNodesInserted(_tEvent);
			}
		}
		else if(eventType == ShapeModelEvent.EventType.ShapeRemoved) {
			for (TreeModelListener lis: _treeListeners) {
				lis.treeNodesRemoved(_tEvent);
			}
		}
	}
	
}
