import type Language_1 from "./Language.js";
import type Meaning_1 from "./Meaning.js";
import type Pronunciation_1 from "./Pronunciation.js";
import type Structure_1 from "./Structure.js";
interface EntityX {
    id?: number;
    structure?: Structure_1;
    pronunciation?: Pronunciation_1;
    meaning?: Meaning_1;
    language?: Language_1;
    description?: string;
    compound: boolean;
    attested: boolean;
    standardised: boolean;
    pronunciationString?: string;
    explanationsString?: string;
    characterString?: string;
    structureId?: string;
}
export default EntityX;
