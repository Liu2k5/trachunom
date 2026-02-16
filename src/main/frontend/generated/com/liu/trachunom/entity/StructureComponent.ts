import type Structure_1 from "./Structure.js";
import type StructureClassification_1 from "./StructureClassification.js";
import type StructureComponentId_1 from "./StructureComponentId.js";
interface StructureComponent {
    id?: StructureComponentId_1;
    structure?: Structure_1;
    structureComponent?: Structure_1;
    structureClassification?: StructureClassification_1;
    quantity?: number;
}
export default StructureComponent;
