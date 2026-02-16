import type EntityCompositionId_1 from "./EntityCompositionId.js";
import type EntityX_1 from "./EntityX.js";
interface EntityComposition {
    id?: EntityCompositionId_1;
    parentEntity?: EntityX_1;
    childEntity?: EntityX_1;
}
export default EntityComposition;
