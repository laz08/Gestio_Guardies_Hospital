package prop;
class Aresta {
	int v, w;
	long long int flow, capacitat;
	
	public void Aresta(int vertexv, int vertexw, long long int f, long long int cap) {
		v = vertexv;
		w = vertexw;
		flow = f;
		if (cap != null) capacitat = cap;
		else capacitat = 0;
	}
	
	public void Aresta(Aresta a) {
		v = a.v;
		w = a.w;
		flow = a.flow;
		capacitat = a.capacitat;
	}
	
	public int getv() {
		return v;
	}
	
	public int getw() {
		return w;
	}
	
	public Boolean full() {
		return flow == capacitat;
	}
	
	public void setv(int vertex) {
		v = vertex;
	}
	
	public void setw(int vertex) {
		w = vertex;
	}
	
	public void setflow(long long int f) {
		flow = f;
	}
	
	public int getflow() {
		return flow;
	}
	
	public void resetflow() {
		flow = 0;
	}
	
	public void addresflow(int vertex, long long int f) {
		if (vertex == v) flow -= f;
		else if (vertex == w) flow += f; 
	}
	
	public long long int capres(int vertex) {
		if (vertex == v) return flow;
		else if (vertex == w) return capacitat-flow;
	}
}