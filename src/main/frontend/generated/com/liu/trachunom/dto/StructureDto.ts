import type CharacterDto_1 from "./CharacterDto.js";
interface StructureDto {
    id?: number;
    character?: CharacterDto_1;
    structureTypeId?: number;
    width?: number;
    height?: number;
    innerWidth?: number;
    innerHeight?: number;
    characterString?: string;
    characterWithPronunciationsString?: string;
}
export default StructureDto;
