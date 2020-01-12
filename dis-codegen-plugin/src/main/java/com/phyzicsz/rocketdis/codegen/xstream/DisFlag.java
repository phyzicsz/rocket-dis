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

import com.phyzicsz.rocketdis.codegen.xstream.converters.DisFlagConverter;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import java.util.Optional;

/**
 *
 * @author phyzicsz
 */
@XStreamConverter(DisFlagConverter.class)
public class DisFlag {
    
    @XStreamAsAttribute
    private Optional<String> mask;
    
    @XStreamAsAttribute
    private Optional<String> name;
    
    @XStreamAsAttribute
    private Optional<String> comment;
    
    

    public Optional<String> getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = Optional.ofNullable(mask);
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Optional.ofNullable(name);
    }

    public Optional<String> getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = Optional.ofNullable(comment);
    }
    
    
}
