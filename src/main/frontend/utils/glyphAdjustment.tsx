import {useEffect, useState} from "react";
import Structure from "Frontend/generated/com/liu/trachunom/entity/structure/Structure";
import {StructureService} from "Frontend/generated/endpoints";
import {inSupportedCjkRange} from "Frontend/utils/displayTroubleshooter";

export {
    GlyphAdjustment as GlyphAdjustment,
    findAdjustment as findAdjustment,
};
function GlyphAdjustment({structureId, structureType, fontSize, index}: {structureId: number, structureType: string, blankColour: string, fontSize: [number, number], index: number}): JSX.Element | undefined {
    const [structure, setStructure] = useState<Structure | undefined>(undefined);
    useEffect(() => {
        StructureService.findById(structureId).then(o => setStructure(o));
    }, []);
    let character = structure?.characterString ?? '';

    let adjustment = findAdjustment(character, structureType, index);

    // console.log('structureType ' + structureType + " " + adjustment);

    let glyph = adjustment[0] == '' ? character : adjustment[0];
    let scaleX = adjustment[1] * 1.1; // to make gaps narrower
    let scaleY = adjustment[2] * 1.1;
    let rightMove = adjustment[3];
    let bottomMove = adjustment[4];
    let blankStartX = adjustment[5];
    let blankStartY = adjustment[6];
    let blankEndX = adjustment[7];
    let blankEndY = adjustment[8];

    let width = (blankEndX - blankStartX) + 'em';
    let height = (blankEndY - blankStartY) + 'em';

    return (
        <div
            style={{
                position: 'relative',
                transform: 'scale(' + scaleX * fontSize[0] + ', ' + scaleY * fontSize[1] + ')',
                WebkitMaskImage: `linear-gradient(#000, #000), linear-gradient(#000, #000)`,
                WebkitMaskSize: `${width} ${height}, 100% 100%`,
                WebkitMaskPosition: `${blankStartX}em ${blankStartY}em, 0 0`,
                WebkitMaskRepeat: `no-repeat`,
                WebkitMaskComposite: `destination-out`,
                maskComposite: `exclude`,
            }}
        >
            <div
                style={{
                    position: 'relative',
                    left: rightMove + 'em',
                    top: bottomMove + 'em',
                }}
            >
                {glyph}
            </div>
        </div>
    );
}

function findAdjustment(character: string, structureType: string, index: number):
    [string, number, number, number, number, number, number, number, number] {
    for (let i = 0; i < data.length; i++) {
        if (data[i][0] === character && data[i][1] === structureType && data[i][2] === index) {
            if (inSupportedCjkRange(character.codePointAt(0) ?? 0)) {
                return [data[i][3], data[i][4], data[i][5], data[i][6], data[i][7],
                    data[i][8], data[i][9], data[i][10], data[i][11]];
            } else break;
        }
    }
    return ['', 1, 1, 0, 0, 0, 0, 0, 0];
}

// character, structureType, index, glyph, scaleX, scaleY, rightMove, bottomMove, blankStartX, blankStartY, blankEndX, blankEndY
const data: [string, string, number, string, number, number, number, number, number, number, number, number][] = [
    // ⿰⿲⿱⿳⿸⿺⿹⿽⿵⿷⿶⿼⿴⿻
    // as default font on each os displays these single radicals differently in position of a square
    // (centre or leftmost), it is recommended to adjust the compound characters to prevent it,
    // like 礼 in lieu of 礻
    ['扌', '⿰',   0,  '' , 2,   1,   0,   0,    0,   0,   0,    0],
    ['礻', '⿰',   0, '礼', 2,   1,0.15,   0, 0.70, 0.0,1.75,  1.1],
    ['衤', '⿰',   0, '补', 2,   1,0.15,   0, 0.75, 0.0,1.75,  1.1],
    // ['礻', '⿰',   0,   '', 2.5,   1,0.,   0, 0.75, 0.0,1.25,  1],
    ['彳', '⿰',   0,  '' , 2,   1,   0,   0,    0,   0,   0,    0],
    ['女', '⿰',   0, '如', 2,   1,0.25,   0, 0.8, 0.0,1.25,  1.1],
    ['米', '⿰',   0, '粑', 2.5,   1,0.25,   0, 0.71, 0.0,1.25,  1.1],
    ['口', '⿰',   0, '叫', 2,   1,0.25,   0, 0.7, 0.0,1.5,  1.1],
    ['日', '⿰',   0, '旰', 2,   1,0.25,   0, 0.6, 0.0,1.5,  1.1],
    ['罒', '⿱',   0,   '', 1,   2,   0,   0.25,    0,   0,   0,   0],
    ['⻗', '⿱',   0,   '', 1,   2,   0,   0.25,    0,   0,   0,   0],
    // ['雨', '⿱',   0, '雷', 1,   2,   0,   0.25,    0, 0.77,   1, 1.5],
    ['乚', '⿺',   0,  '' , 1.0,   1,   0.05,   0,   0,   0,   0,   0],
    ['辶', '⿺',   0,  '' ,   1,   1,   0,   0,   0,   0,   0,   0],
    ['囗', '⿴',   0,  '' ,   1,   1,   0,   0, 0.1, 0.1, 0.9, 0.9],
    ['门', '⿵',   0,  '' ,   1,   1,   0,   0, 0.1, 0.3, 0.9, 1.0],
    ['門', '⿵',   0,  '' ,   1,   1,   0,   0, 0.1, 0.3, 0.9, 1.0],
    ['冂', '⿵',   0,  '' ,   1,   1,   0,   0, 0.1, 0.2, 0.9, 1.0],
    ['凵', '⿶',   0,  '' ,   1,   1,   0,   0, 0.1, 0.0, 0.9, 0.8],
    ['匚', '⿷',   0,  '' ,   1,   1,   0,   0, 0.2, 0.1, 1.0, 0.9],
    ['匸', '⿷',   0,  '' ,   1,   1,   0,   0, 0.2, 0.1, 1.0, 0.9],
    ['气', '⿹',   0,  '' ,   1,   1,   0,   0, 0.0, 0.2, 0.8, 1.0],
    ['广', '⿸',   0,  '' ,   1,   1,   0,   0, 0.2, 0.3, 1.0, 1.0],
    ['厂', '⿸',   0,  '' ,   1,   1,   0,   0, 0.2, 0.2, 1.0, 1.0],
    ['尸', '⿸',   0,  '' ,   1,   1,   0,   0, 0.2, 0.3, 1.0, 1.0],
    ['户', '⿸',   0,  '' ,   1,   1,   0,   0, 0.2, 0.3, 1.0, 1.0]
];