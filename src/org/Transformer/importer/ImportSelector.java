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

import org.Transformer.Job;
import org.Transformer.JobSerialize;
import org.Transformer.Slides.ConfigurationSlide;
import org.Transformer.dataset.DataSet;

/**
 * @author Lars P&ouml;tter
 * (<a href=mailto:Lars_Poetter@gmx.de>Lars_Poetter@gmx.de</a>)
 */
public abstract class ImportSelector implements JobSerialize, ConfigurationSlide
{
    protected DataSet[] data = new DataSet[1];

    /**
     *
     */
    public ImportSelector()
    {
        data[0] = new DataSet();
    }

    public abstract boolean parseToDataSets(InputStream src);

    public DataSet[] getTheData()
    {
        return data;
    }

    @Override
    public ConfigurationSlide getNextSlide()
    {
        return null;
    }

    @Override
    public boolean hasNextSlide()
    {
        return false;
    }

    @Override
    public void setJob(Job theJob)
    {
    }

    @Override
    public Job getJob()
    {
        return null;
    }

}
