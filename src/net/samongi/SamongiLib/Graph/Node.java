package net.samongi.SamongiLib.Graph;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * Represents a Node in a graph.
 */
public class Node {
    // ---------------------------------------------------------------------------------------------------------------//
    private final Graph m_graph;
    private final int m_id;

    private Map<Integer, Edge> m_undirectedEdges = new HashMap<>();
    private Map<Integer, Edge> m_incomingEdges = new HashMap<>();
    private Map<Integer, Edge> m_outgoingEdges = new HashMap<>();

    // ---------------------------------------------------------------------------------------------------------------//
    public Node(@Nonnull Graph graph, int id)
    {
        m_graph = graph;
        m_id = id;
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public int getId()
    {
        return m_id;
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public Edge makeEdge(@Nonnull Node other, boolean directed)
    {
        Edge edge = new Edge(this, other, directed);

        if(directed){
            m_outgoingEdges.put(other.getId(), edge);
            other.m_incomingEdges.put(this.getId(), edge);
        } else {
            m_undirectedEdges.put(other.getId(), edge);
            other.m_undirectedEdges.put(this.getId(), edge);
        }

        return edge;
    }

    public Edge makeEdge(@Nonnull Node other)
    {
        return makeEdge(other, false);
    }

    public void removeEdge(@Nonnull Edge edge)
    {
        if(edge.isDirected())
        {
            if(edge.getOriginatingNode() == this)
            {
                m_outgoingEdges.remove(edge.getDestinationNode().getId());
                edge.getDestinationNode().m_incomingEdges.remove(getId());
            }
            if(edge.getDestinationNode() == this)
            {
                m_incomingEdges.remove(edge.getOriginatingNode().getId());
                edge.getOriginatingNode().m_outgoingEdges.remove(getId());
            }
        } else
        {
            m_undirectedEdges.remove(edge.getOther(this));
        }
    }

    public void clearEdges()
    {
        for(Edge edge : m_undirectedEdges.values())
        {
            edge.remove();
        }
        for(Edge edge : m_incomingEdges.values())
        {
            edge.remove();
        }
        for(Edge edge : m_outgoingEdges.values())
        {
            edge.remove();
        }
    }

    public boolean hasEdge(@Nonnull Node other)
    {
        return getEdge(other) == null;
    }
    public Edge getEdge(@Nonnull Node other)
    {
        int otherId = other.getId();
        if(this.m_undirectedEdges.containsKey(otherId))
        {
            return m_undirectedEdges.get(otherId);
        }
        if(this.m_incomingEdges.containsKey(otherId))
        {
            return m_incomingEdges.get(otherId);
        }
        if(this.m_outgoingEdges.containsKey(otherId))
        {
            return m_outgoingEdges.get(otherId);
        }

        return null;
    }
    public List<Edge> getEdges()
    {
        List<Edge> edges = new ArrayList<>();
        edges.addAll(m_outgoingEdges.values());
        edges.addAll(m_incomingEdges.values());
        edges.addAll(m_undirectedEdges.values());
        return edges;
    }
    public List<Edge> getIncomingEdges()
    {
        List<Edge> edges = new ArrayList<>();
        edges.addAll(m_incomingEdges.values());
        edges.addAll(m_undirectedEdges.values());
        return edges;
    }
    public List<Edge> getOutgoingEdges()
    {
        List<Edge> edges = new ArrayList<>();
        edges.addAll(m_outgoingEdges.values());
        edges.addAll(m_undirectedEdges.values());
        return edges;
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public boolean hasPath(@Nonnull Node target)
    {
        return getPath(target) != null;
    }
    public boolean cyclesSelf()
    {
        return hasPath(this);
    }

    public List<Node> getPath(@Nonnull Node target)
    {
        Queue<Node> nodeQueue = new LinkedList<>();
        // Node -> Parent (also counts as marked)
        Map<Node, Node> parents = new HashMap();

        for(Edge edge : getOutgoingEdges())
        {
            Node newNode = edge.getOther(this);
            nodeQueue.add(newNode);
            parents.put(newNode, this);
        }

        Node current = null;
        boolean found = false;
        while(!nodeQueue.isEmpty())
        {
            current = nodeQueue.poll();
            if(current.equals(target))
            {
                found = true;
                break;
            }

            for(Edge edge : current.getOutgoingEdges())
            {
                Node newNode = edge.getOther(current);
                if(parents.containsKey(newNode))
                {
                    continue;
                }
                nodeQueue.add(newNode);
                parents.put(newNode, current);
            }
        }
        if(!found)
        {
            return null;
        }

        List<Node> path = new ArrayList<>();

        // We need to add the first just in case it is already this.
        path.add(current);
        current = parents.get(current);

        while(current != this)
        {
            path.add(current);
            current = parents.get(current);
            path.add(current);
        }
        return path;
    }

    // ---------------------------------------------------------------------------------------------------------------//


    public boolean equals(Node other)
    {
        return m_id == other.m_id && m_graph == other.m_graph;
    }

    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Node)
        {
            return equals((Node) other);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return m_graph.hashCode() + 31 * m_id;
    }
}
