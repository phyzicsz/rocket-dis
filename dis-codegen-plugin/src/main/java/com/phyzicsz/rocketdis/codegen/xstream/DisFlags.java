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

import com.phyzicsz.rocketdis.codegen.xstream.converters.DisFlagsConverter;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author phyzicsz
 */
@XStreamConverter(DisFlagsConverter.class)
public class DisFlags extends AbstractAttribute implements AbstractAttributeCodeGeneration{
    
    @XStreamImplicit(itemFieldName="flag")
    private List<DisFlag> flags = new ArrayList<>();

    public void addFlag(DisFlag flag){
        flags.add(flag);
    }
    
    public List<DisFlag> getFlags() {
        return flags;
    }

    public void setFlags(List<DisFlag> flags) {
        this.flags = flags;
    }

    @Override
    public Optional<TypeName> typeName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<String> typeSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<FieldSpec> fieldSpec() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
