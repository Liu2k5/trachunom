import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type TradSimpStandardDto_1 from "./com/liu/trachunom/dto/TradSimpStandardDto.js";
import type TradSimpStandardId_1 from "./com/liu/trachunom/entity/TradSimpStandardId.js";
import client_1 from "./connect-client.default.js";
async function delete_1(tradSimpStandardId: TradSimpStandardId_1 | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("TradSimpStandardEndpoint", "delete", { tradSimpStandardId }, init); }
async function save_1(tradSimpStandardDto: TradSimpStandardDto_1 | undefined, init?: EndpointRequestInit_1): Promise<TradSimpStandardDto_1 | undefined> { return client_1.call("TradSimpStandardEndpoint", "save", { tradSimpStandardDto }, init); }
export { delete_1 as delete, save_1 as save };
