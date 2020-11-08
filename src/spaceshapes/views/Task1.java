package spaceshapes.views;

import spaceshapes.Shape;

import spaceshapes.CarrierShape;

import java.util.ArrayList;
import java.util.List;


import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.ShapeModel;

/**
 * A key part of a ShapeModel object is its composite structure of Shapes. This class will allow the
 * Space-Shapes application to have a hierarchical view of this composition structure. This is done
 * by reusing Swing’s JTree component which is intended for visualising hierarchical structures.
 * 
 * @author Jia Qi
 *
 */

public class Task1 implements TreeModel{
	
	private ShapeModel _model;
	protected List<TreeModelListener> _treeListeners = new ArrayList<TreeModelListener>();
	
	public Task1(ShapeModel o) {
		_model = o;
	}
	
	public Object getRoot() {
		return _model.root();
	}
	
	public int getChildCount(Object parent) {
		int result = 0;
		
		if(parent instanceof CarrierShape) {
			CarrierShape shape = (CarrierShape) parent;
			result = shape._childShape.size();
		}
		return result;
	}
	
	public boolean isLeaf(Object node) {
		return !(node instanceof CarrierShape);
	}
	
	public Object getChild(Object parent, int index) {
		if (parent instanceof CarrierShape) {
			CarrierShape shape = (CarrierShape) parent;
			if(shape._childShape.size() > index) {
				return shape._childShape.get(index);
			}
		}
		return null;
	}
	
	public int getIndexOfChild(Object parent, Object child) {
		int indexOfChild = -1;
		
		if(parent instanceof CarrierShape) {
			CarrierShape shape = (CarrierShape) parent;
			boolean found = false;
			int index = 0;
			for (Shape i : shape._childShape) {
				if (child == i && !found) {
					found = true;
					indexOfChild = index;
				}
				else {
					index++;
				}
			}
		}
		return indexOfChild;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {}

	public void addTreeModelListener(TreeModelListener l) {
		_treeListeners.add(l);
	}

	public void removeTreeModelListener(TreeModelListener l) {
		_treeListeners.remove(l);
	}
}
