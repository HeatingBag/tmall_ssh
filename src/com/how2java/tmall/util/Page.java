/**
* @author 作者 E-mail:
* @version 创建时间：2022年5月29日 上午8:06:55
*/
package com.how2java.tmall.util;

public class Page {

	int start;// 开始页数
	int count;// 每页显示个数
	int total;// 总个数
	String param;// 参数

	private static final int defaultCount = 5;// 默认每页显示5条

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Page() {
		count = defaultCount;
	}

	public Page(int start, int count) {
		super();
		this.start = start;
		this.count = count;
	}

	// isHasPreviouse 判断是否有前一页
	public boolean isHasPreviouse() {
		if (start == 0)
			return false;

		return true;
	}

	// isHasNext 判断是否有后一页
	public boolean isHasNext() {
		if (start == getLast())
			return false;

		return true;
	}

	// getTotalPage 根据 每页显示的数量count以及总共有多少条数据total，计算出总共有多少页
	public int getTotalPage() {
		int totalPage;
		// 假设总数是50，是能够被5整除的，那么就有10页
		if (0 == total % count)
			totalPage = total / count;

		// 假设总数是51，不能够被5整除的，那么就有11页
		else
			totalPage = total / count + 1;

		if (0 == totalPage)
			totalPage = 1;

		return totalPage;

	}

	// getLast 计算出最后一页的数值是多少
	public int getLast() {
		int last;

		// 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
		if (0 == total % count)
			last = total - count;

		// 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
		else
			last = total - total % count;

		last = last < 0 ? 0 : last;

		return last;
	}

	public String toString() {
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
				+ ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
				+ isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
	}

}
