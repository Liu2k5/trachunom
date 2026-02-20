import type Explanation_1 from "./Explanation.js";
import type Meaning_1 from "./Meaning.js";
interface Meaning {
    id?: number;
    origin?: Meaning_1;
    explanations?: Array<Explanation_1 | undefined>;
    explanationsString?: string;
}
export default Meaning;
