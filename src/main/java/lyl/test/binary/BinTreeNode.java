package lyl.test.binary;

public class BinTreeNode {
	
	private Object data;
	private BinTreeNode parent;
	private BinTreeNode lChild;
	private BinTreeNode rChild;
	
	private int height;
	private int size;
	
	public BinTreeNode() {
		this(null);
	}
	
	public BinTreeNode(Object e) {
		data = e;
		height = 0;
		size = 1;
		parent = lChild = rChild = null;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object obj) {
		data = obj;
	}

	public void setParent(BinTreeNode parent) {
		this.parent = parent;
	}

	public BinTreeNode getlChild() {
		return lChild;
	}

	public void setlChild(BinTreeNode lChild) {
		this.lChild = lChild;
	}

	public BinTreeNode getrChild() {
		return rChild;
	}

	public void setrChild(BinTreeNode rChild) {
		this.rChild = rChild;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public boolean hasParent() {
		return parent != null;
	}
	
	public boolean hasLChild() {
		return lChild != null;
	}
	
	public boolean hasRChild() {
		return rChild != null;
	}
	
	public boolean isLeaf() {
		return !hasLChild() && !hasRChild();
	}
	
	public boolean isLChild() {
		return (hasParent() && this == parent.lChild);
	}
	
	public boolean isRChild() {
		return (hasParent() && this == parent.rChild);
	}
	
	public int getHeight() {
		return height;
	}
	
	public void updateHeight() {
		int newH = 0;
		if(hasLChild()) {
			newH = Math.max(newH, 1+getlChild().getHeight());
		}
		if(hasRChild()) {
			newH = Math.max(newH, 1+getrChild().getHeight());
		}
		if(newH == height) {
			return;
		}
		height = newH;
		if(hasParent()) {
			getParent().updateHeight();  // 递归更新祖先的高度
		}	
	}
	
	public void updateSize() {
		size = 1;
		if(hasLChild()) {
			size += getlChild().getSize();
		}
		if(hasRChild()) {
			size += getrChild().getSize();
		}
		if(hasParent()) {
			getParent().updateSize();   // 递归更新祖先的规模
		}
	}
	
	public BinTreeNode getParent() {
		return parent;
	}
	
	// 断开于父亲的关系
	public void sever() {
		if(!hasParent()) {
			return;
		}
		if(isLChild()) {
			parent.lChild = null;
		} else {
			parent.rChild = null;
		}
		parent.updateHeight(); // 更新父结点及其祖先
		parent.updateSize();   // 更新父结点及其祖先规模
		parent = null;
	}
	
	public BinTreeNode getLChild() {
		return lChild;
	}
	
	// 设置当前结点的左孩子 返回原右孩子
	public BinTreeNode setLChild(BinTreeNode lc) {
		BinTreeNode oldLc = this.lChild;
		if(hasLChild()) {
			lChild.sever(); // 断开当前左孩子与结点的关系
		}
		if(lc != null) {
			lc.sever();     // 断开lc与其父节点的关系
			this.lChild =lc;    // 确定父子关系
			lc.parent = this;
			this.updateHeight();   // 更新当前结点及其祖先高度
			this.updateSize();
		}
		return oldLc;
	}

}
