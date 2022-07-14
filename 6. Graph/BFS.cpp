#include <bits/stdc++.h>
using namespace std;

class Graph
{
public:
    int V; // No. of vertices
    // Pointer to an array containing adjacency lists
    vector<list<int>> adj;

public:
    Graph(int V)
    {
        this->V = V;
        adj.resize(V);
    }

    // function to add an edge to graph
    void addEdge(int v, int w)
    {
        adj[v].push_back(w);
    }

    // prints BFS traversal from a given source s
    void BFS(int s);
};

void Graph::BFS(int s)
{
    // Mark all the vertices as not visited
    vector<bool> visited;
    visited.resize(V, false);

    // Create a queue for BFS
    list<int> queue;

    // Mark the current node as visited and enqueue it
    visited[s] = true;
    queue.push_back(s);

    while (!queue.empty())
    {
        // Dequeue a vertex from queue and print it
        s = queue.front();
        cout << s << " ";
        queue.pop_front();

        // Get all adjacent vertices of the dequeued
        // vertex s. If a adjacent has not been visited,
        // then mark it visited and enqueue it
        for (auto adjecent : adj[s])
        {
            if (!visited[adjecent])
            {
                visited[adjecent] = true;
                queue.push_back(adjecent);
            }
        }
    }
}

vector<int> bfsOfGraph(int V, vector<list<int>> adj)
{
    vector<int> bfs_traversal;
    vector<bool> vis(V, false);
    for (int i = 0; i < V; ++i)
    {
        if (!vis[i])
        {
            queue<int> q;
            vis[i] = true;
            q.push(i);
            while (!q.empty())
            {
                int g_node = q.front();
                q.pop();
                bfs_traversal.push_back(g_node);
                for (auto it : adj[g_node])
                {
                    if (!vis[it])
                    {
                        vis[it] = true;
                        q.push(it);
                    }
                }
            }
        }
    }
    return bfs_traversal;
}

int main()
{
    // Create a graph given in the above diagram
    Graph g(4);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(2, 3);
    g.addEdge(3, 3);

    cout << "BFS is (starting from vertex 2) : ";
    g.BFS(0);
    vector<int> v;
    v = bfsOfGraph(4, g.adj);
    for (auto i : v)
        cout << i << " ";

    return 0;
}
