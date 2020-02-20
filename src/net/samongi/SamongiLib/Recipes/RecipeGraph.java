package net.samongi.SamongiLib.Recipes;

import net.samongi.SamongiLib.Graph.Graph;
import net.samongi.SamongiLib.Graph.Node;
import net.samongi.SamongiLib.SamongiLib;
import org.bukkit.Material;
import org.bukkit.inventory.*;

import javax.annotation.Nonnull;
import java.util.*;

public class RecipeGraph {
    // ---------------------------------------------------------------------------------------------------------------//
    private Graph m_graph = new Graph();
    private Map<Material, Integer> m_materialMapping = new HashMap<>();
    private Map<Integer, Material> m_graphIdMapping = new HashMap<>();

    // ---------------------------------------------------------------------------------------------------------------//
    public int ensureMaterial(Material material)
    {
        if(!m_materialMapping.containsKey(material))
        {
            Node node = m_graph.makeNode();
            m_materialMapping.put(material, node.getId());
            m_graphIdMapping.put(node.getId(), material);
        }
        return m_materialMapping.get(material);
    }

    public void addRelation(Material ingredient, Material result)
    {
        int ingredientId = ensureMaterial(ingredient);
        int resultId = ensureMaterial(result);

        Node ingredientNode = m_graph.getNode(ingredientId);
        Node resultNode = m_graph.getNode(resultId);

        ingredientNode.makeEdge(resultNode, true);
    }

    public void addRecipe(@Nonnull Recipe recipe)
    {
        Material resultMaterial = recipe.getResult().getType();

        // We are now going to get a bunch of materials that can possibly be used to craft the result.
        Set<Material> componentMaterials = new HashSet<>();
        if(recipe instanceof ShapedRecipe)
        {
            ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
            for(ItemStack ingredient : shapedRecipe.getIngredientMap().values())
            {
                if(ingredient == null)
                {
                    continue;
                }
                componentMaterials.add(ingredient.getType());
            }
            for(RecipeChoice ingredient : shapedRecipe.getChoiceMap().values()) {
                if(ingredient == null)
                {
                    continue;
                }
                if (!(ingredient instanceof RecipeChoice.MaterialChoice)) {
                    continue;
                }
                RecipeChoice.MaterialChoice ingredientChoice = (RecipeChoice.MaterialChoice) ingredient;
                componentMaterials.addAll(((RecipeChoice.MaterialChoice) ingredient).getChoices());
            }
        }
        if(recipe instanceof ShapelessRecipe)
        {
            ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
            for(ItemStack ingredient : shapelessRecipe.getIngredientList())
            {
                if(ingredient == null)
                {
                    continue;
                }
                componentMaterials.add(ingredient.getType());
            }
            for(RecipeChoice ingredient : shapelessRecipe.getChoiceList()) {
                if(ingredient == null)
                {
                    continue;
                }
                if (!(ingredient instanceof RecipeChoice.MaterialChoice)) {
                    continue;
                }
                RecipeChoice.MaterialChoice ingredientChoice = (RecipeChoice.MaterialChoice) ingredient;
                componentMaterials.addAll(((RecipeChoice.MaterialChoice) ingredient).getChoices());
            }
        }

        for(Material material : componentMaterials)
        {
            addRelation(material, resultMaterial);
        }
    }

    public boolean doesCycle(Material material)
    {
        int materialId = m_materialMapping.get(material);
        Node materialNode = m_graph.getNode(materialId);
        return materialNode.cyclesSelf();
    }

    /**
     * Returns a list of materials that are used to craft themselves at some point.
     * @return
     */
    public List<Material> getCycles()
    {
        List<Material> cyclingMaterials = new ArrayList<>();
        for(Material material : m_materialMapping.keySet())
        {
            if(doesCycle(material))
            {
                cyclingMaterials.add(material);
            }
        }
        return cyclingMaterials;
    }
}
