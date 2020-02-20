package net.samongi.SamongiLib.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    // ---------------------------------------------------------------------------------------------------------------//
    private Map<Integer, Node> m_nodes = new HashMap<>();
    private int m_nodeCounter = 0;

    // ---------------------------------------------------------------------------------------------------------------//
    public Graph()
    {
    }

    // ---------------------------------------------------------------------------------------------------------------//
    public List<Node> getNodes()
    {
        return new ArrayList<Node>(m_nodes.values());
    }

    public boolean hasNode(int id)
    {
        return m_nodes.containsKey(id);
    }

    public Node getNode(int id)
    {
        return m_nodes.get(id);
    }

    public Node makeNode()
    {
        Node node = new Node(this, ++m_nodeCounter);
        m_nodes.put(node.getId(), node);
        return node;
    }

    public void removeNode(Node node)
    {
        m_nodes.remove(node.getId());
        node.clearEdges();
    }
    public void removeNode(int id)
    {
        if(!hasNode(id))
        {
            return;
        }
        removeNode(getNode(id));
    }

    // ---------------------------------------------------------------------------------------------------------------//

}
