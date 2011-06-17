/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>
 *
 */

/**
 *
 */
package org.Transformer.importer;

import java.io.InputStream;
import java.util.Map;
import java.util.Vector;

import org.Transformer.JobUtils;
import org.Transformer.dataset.DataSet;

/**
 * @author Lars P&ouml;tter
 * (<a href=mailto:Lars_Poetter@gmx.de>Lars_Poetter@gmx.de</a>)
 */
public class TreeImportSelector extends ImportSelector
{
    private String[] mappingPosition;
    private String[] mappingName;

    /**
     *
     */
    public TreeImportSelector()
    {
        super();
    }

    public final void setMapping(final String[] positions, final String[] Names)
    {
        mappingName = Names;
        mappingPosition = positions;
    }

    @Override
    public final boolean parseToDataSets(final InputStream src)
    {
        final TreeStructure Tree = new HtmlTreeStructure(src);
        if((null == mappingPosition) || (null == mappingName))
        {
            System.out.println("No Mapping !");
            return false;
        }
        final Vector<DataSet> res = new Vector<DataSet>();
        System.out.println("Importing with " + mappingPosition.length + " Mapping Positions.");
        for(int i = 0; i < mappingPosition.length; i++)
        {
            final String[] Val = Tree.getLeafsFor(mappingPosition[i]);
            if(null == Val)
            {
                continue;
            }
            if(1 > Val.length)
            {
                continue;
            }
            System.out.println("Found " + Val.length + " Strings.");
            for(int k = 0; k < Val.length; k++)
            {
                DataSet curRow;
                if(res.size()> k)
                {
                    curRow = res.get(k);
                }
                else
                {
                    curRow = new DataSet();
                    res.add(k, curRow);
                }
                System.out.println("String " + k + " : " + Val[k]);
                curRow.addDataAtom(Val[k], mappingName[i]);
            }
        }
        setDataSet((DataSet[])res.toArray(new DataSet[1]));
        return true;
    }

    @Override
    public final String getConfig()
    {
        return "" + JobUtils.getConfigTextFor(mappingPosition, "Mapping_Position") + "\n"
                  + JobUtils.getConfigTextFor(mappingName, "Mapping_Name");
    }

    @Override
    public final void setConfig(final Map<String, String> cfg)
    {
        mappingPosition = JobUtils.getStringArrayFromSettingMap(cfg, "Mapping_Position");
        mappingName = JobUtils.getStringArrayFromSettingMap(cfg, "Mapping_Name");
    }

    @Override
    public final String getName()
    {
        return "TreeImportSelector";
    }

}
