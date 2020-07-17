/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.core.waiters;

import java.util.List;
import java.util.function.Consumer;
import software.amazon.awssdk.annotations.SdkPublicApi;

@SdkPublicApi
public interface WaiterBuilder<T, B> {
    /**
     * Defines a list of {@link WaiterAcceptor}s to check whether an expected state has met after executing an operation.
     *
     * <p>
     * The SDK will iterate over the acceptors list and the first acceptor to match the result of the operation transitions
     * the waiter to the state specified in the acceptor.
     *
     * <p>
     * This completely overrides any WaiterAcceptor currently configured in the builder via
     * {@link #addAcceptor(WaiterAcceptor)}
     *
     * @param waiterAcceptors the waiter acceptors
     * @return a reference to this object so that method calls can be chained together.
     */
    B acceptors(List<WaiterAcceptor<T>> waiterAcceptors);

    /**
     * Adds a {@link WaiterAcceptor} to the end of the ordered waiterAcceptors list.
     *
     * <p>
     * The SDK will iterate over the acceptors list and the first acceptor to match the result of the operation transitions
     * the waiter to the state specified in the acceptor.
     *
     * @param waiterAcceptors the waiter acceptors
     * @return a reference to this object so that method calls can be chained together.
     */
    B addAcceptor(WaiterAcceptor<T> waiterAcceptors);

    /**
     * Defines a {@link PollingStrategy} to use when polling a resource
     *
     * @param pollingStrategy the polling strategy to use
     * @return a reference to this object so that method calls can be chained together.
     */
    B pollingStrategy(PollingStrategy pollingStrategy);

    /**
     * Defines a {@link PollingStrategy} to use when polling a resource
     *
     * @param pollingStrategy the polling strategy to use
     * @return a reference to this object so that method calls can be chained together.
     */
    default B pollingStrategy(Consumer<PollingStrategy.Builder> pollingStrategy) {
        PollingStrategy.Builder builder = PollingStrategy.builder();
        pollingStrategy.accept(builder);
        return pollingStrategy(builder.build());
    }

}
