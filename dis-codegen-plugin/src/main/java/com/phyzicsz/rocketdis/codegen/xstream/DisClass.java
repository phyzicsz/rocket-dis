/*
 * Copyright 2019 phyzicsz.
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
package com.phyzicsz.rocketdis.codegen.xstream;


import com.phyzicsz.rocketdis.codegen.xstream.converters.DisClassConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author phyzicsz
 */
@XStreamConverter(DisClassConverter.class)
public class DisClass{

    @XStreamAsAttribute
    private Optional<String> name = Optional.empty();

    @XStreamAsAttribute
    @XStreamAlias("inheritsFrom")
    private Optional<String> parent = Optional.empty();

    @XStreamAsAttribute
    private Optional<String> comment = Optional.empty();

    @XStreamImplicit(itemFieldName="attribute")
    private List<DisAttribute> attributes = new ArrayList<>();
    
    @XStreamImplicit(itemFieldName="initialValue")
    private List<DisInitialValue> initialValue = new ArrayList<>();
   

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = Optional.ofNullable(parent);
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = Optional.ofNullable(comment);
    }
    

    public void addAttribute(DisAttribute attribute) {
        this.attributes.add(attribute);
    }

    public List<DisAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DisAttribute> attributes) {
        this.attributes = attributes;
    }
    
    public void addInitialValue(DisInitialValue initialValue) {
        this.initialValue.add(initialValue);
    } 

    public List<DisInitialValue> getInitialValues() {
        return initialValue;
    }

    public void setInitialValues(List<DisInitialValue> initialValue) {
        this.initialValue = initialValue;
    } 

    
}
