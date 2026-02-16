import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type CharacterDto_1 from "./com/liu/trachunom/dto/CharacterDto.js";
import type CharacterX_1 from "./com/liu/trachunom/entity/CharacterX.js";
import client_1 from "./connect-client.default.js";
async function delete_1(unicode: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("CharacterEndpoint", "delete", { unicode }, init); }
async function save_1(characterDto: CharacterDto_1 | undefined, init?: EndpointRequestInit_1): Promise<CharacterX_1 | undefined> { return client_1.call("CharacterEndpoint", "save", { characterDto }, init); }
export { delete_1 as delete, save_1 as save };
