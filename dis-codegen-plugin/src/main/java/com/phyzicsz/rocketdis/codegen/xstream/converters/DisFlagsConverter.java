/*
 * Copyright 2020 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.rocketdis.codegen.xstream.converters;

import com.phyzicsz.rocketdis.codegen.xstream.DisFlag;
import com.phyzicsz.rocketdis.codegen.xstream.DisFlags;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class DisFlagsConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisFlagsConverter.class);
    
    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        DisFlags flags = new DisFlags();
        
        //get the attributes...
        for (Iterator<String> iter = reader.getAttributeNames(); iter.hasNext();) {
            String name = iter.next();
            switch (name) {
                default:
                    LOGGER.info("unsupported attribute: {}", name);
                    break;
            }
        }
        
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            switch (nodeName){
                case "flag":
                    DisFlag flag = (DisFlag)context.convertAnother(flags, DisFlag.class);
                    flags.addFlag(flag);
                    break;
                
                default:
                    LOGGER.info("unhandled attribute type: {}", nodeName);
                        
            }
            reader.moveUp();
        }
        return flags;
    }
    
    @Override
    public boolean canConvert(Class type) {
        return DisFlags.class.isAssignableFrom(type);
    }
}
