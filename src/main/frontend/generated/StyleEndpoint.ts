import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type StyleDto_1 from "./com/liu/trachunom/dto/StyleDto.js";
import type Style_1 from "./com/liu/trachunom/entity/Style.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("StyleEndpoint", "delete", { id }, init); }
async function save_1(styleDto: StyleDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Style_1 | undefined> { return client_1.call("StyleEndpoint", "save", { styleDto }, init); }
export { delete_1 as delete, save_1 as save };
