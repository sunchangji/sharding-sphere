/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package io.shardingjdbc.core.api.config.strategy;

import io.shardingjdbc.core.routing.strategy.ShardingAlgorithmFactory;
import io.shardingjdbc.core.routing.strategy.ShardingStrategy;
import io.shardingjdbc.core.api.algorithm.complex.ComplexKeysShardingAlgorithm;
import io.shardingjdbc.core.routing.strategy.complex.ComplexShardingStrategy;
import io.shardingjdbc.core.util.StringUtil;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;

/**
 * Complex sharding strategy configuration.
 * 
 * @author zhangliang
 */
@Getter
@Setter
public class ComplexShardingStrategyConfiguration implements ShardingStrategyConfiguration {
    
    private String shardingColumns;
    
    private String algorithmClassName;
    
    @Override
    public ShardingStrategy build() {
        Preconditions.checkNotNull(shardingColumns, "Sharding columns cannot be null.");
        Preconditions.checkNotNull(algorithmClassName, "Algorithm class cannot be null.");
        return new ComplexShardingStrategy(StringUtil.splitWithComma(shardingColumns), ShardingAlgorithmFactory.newInstance(algorithmClassName, ComplexKeysShardingAlgorithm.class));
    }
}