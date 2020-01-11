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
package com.phyzicsz.rocketdis.codegen.api;

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
public class DisClassConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(DisClassConverter.class);
    
    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        DisClass dis = new DisClass();
        
        //get the attributes...
        for (Iterator<String> iter = reader.getAttributeNames(); iter.hasNext();) {
            String name = iter.next();
            switch (name) {
                case "name":
                    dis.setName(reader.getAttribute("name"));
                    break;
                case "inheritsFrom":
                    dis.setParent(reader.getAttribute("inheritsFrom"));
                    break;
                case "comment":
                    dis.setComment(reader.getAttribute("comment"));
                    break;
                default:
                    LOGGER.info("unsupported attribute: {}", name);
                    break;
            }
        }
        
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String nodeName = reader.getNodeName();
            switch (nodeName){
                case "attribute":
                    DisAttribute attr = (DisAttribute)context.convertAnother(dis, DisAttribute.class);
                    dis.addAttribute(attr);
                    break;
                case "initialValue":
                    DisInitialValue initialValue = (DisInitialValue)context.convertAnother(dis, DisInitialValue.class);
                    dis.addInitialValue(initialValue);
                    break;
                default:
                    LOGGER.info("unhandled attribute type: {}", nodeName);
                        
            }
            reader.moveUp();
        }
        return dis;
    }
    
    @Override
    public boolean canConvert(Class type) {
        return DisClass.class.isAssignableFrom(type);
    }
}
