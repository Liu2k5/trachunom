import type EntityDto_1 from "./EntityDto.js";
interface EntityCompositionDto {
    parentEntityId?: number;
    childEntityId?: number;
    childEntity?: EntityDto_1;
    position?: number;
}
export default EntityCompositionDto;
