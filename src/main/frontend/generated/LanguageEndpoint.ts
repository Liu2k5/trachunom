import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type LanguageDto_1 from "./com/liu/trachunom/dto/LanguageDto.js";
import type Language_1 from "./com/liu/trachunom/entity/Language.js";
import client_1 from "./connect-client.default.js";
async function delete_1(id: number | undefined, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("LanguageEndpoint", "delete", { id }, init); }
async function save_1(languageDto: LanguageDto_1 | undefined, init?: EndpointRequestInit_1): Promise<Language_1 | undefined> { return client_1.call("LanguageEndpoint", "save", { languageDto }, init); }
export { delete_1 as delete, save_1 as save };
