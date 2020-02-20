package net.samongi.SamongiLib.Graph;

public class Edge {

    // ---------------------------------------------------------------------------------------------------------------//
    private final Node m_node1;
    private final Node m_node2;
    private final boolean m_directed;

    // ---------------------------------------------------------------------------------------------------------------//
    public Edge(Node node1, Node node2) {
        m_node1 = node1;
        m_node2 = node2;
        m_directed = false;
    }
    public Edge(Node node1, Node node2, boolean directed) {
        m_node1 = node1;
        m_node2 = node2;
        m_directed = directed;
    }
    // ---------------------------------------------------------------------------------------------------------------//

    public boolean isDirected() {
        return m_directed;
    }

    public Node getNode1()
    {
        return m_node1;
    }
    public Node getNode2()
    {
        return m_node2;
    }
    public Node getOriginatingNode()
    {
        return getNode1();
    }
    public Node getDestinationNode()
    {
        return getNode2();
    }

    public Node getOther(Node self)
    {
        if(getNode1() == self)
        {
            return getNode2();
        }
        if(getNode2() == self)
        {
            return getNode1();
        }
        return null;
    }

    public void remove()
    {
        m_node1.removeEdge(this);
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public boolean equals(Edge other) {
        if(m_directed != other.m_directed)
        {
            return false;
        }

        if(m_node1.equals(other.m_node1) && m_node2.equals(other.m_node2))
        {
            // The directed case is correct
            return true;
        } else if(m_directed && other.m_directed) {
            // If they were directed, then they aren't equivalent
            return false;
        }

        return (m_node2.equals(other.m_node1) && m_node1.equals(other.m_node2));
    }
    @Override
    public boolean equals(Object other) {
        if(other instanceof Edge)
        {
            return equals((Edge)other);
        }
        return false;
    }
    // ---------------------------------------------------------------------------------------------------------------//
}
