import {useEffect, useState} from "react";
import Structure from "Frontend/generated/com/liu/trachunom/entity/structure/Structure";
import {StructureService} from "Frontend/generated/endpoints";

export {GlyphAdjustment as GlyphAdjustment};
function GlyphAdjustment({structureId, structureType, blankColour, fontSize}: {structureId: number, structureType: string, blankColour: string, fontSize: [number, number]}): JSX.Element | undefined {
    const [structure, setStructure] = useState<Structure | undefined>(undefined);
    useEffect(() => {
        StructureService.findById(structureId).then(o => setStructure(o));
    }, []);
    let character = structure?.characterString ?? '';

    let adjustment = findAdjustment(character, structureType);

    // console.log('structureType ' + structureType + " " + adjustment);

    let scaleX = adjustment[0];
    let scaleY = adjustment[1];
    let blankSizeX = adjustment[2];
    let blankSizeY = adjustment[3];


    return (
        <div
            style={{
                position: 'relative',
            }}
        >
            <div
                style={{
                    transform: 'scale(' + scaleX * fontSize[0] + ', ' + scaleY * fontSize[1] + ')',
                }}
            >
                {character}
            </div>
            <div
                style={{
                    position: 'absolute',
                    color: blankColour,
                    width: blankSizeX,
                    height: blankSizeY,
                    right: '⿸⿺⿷'.includes(structureType) ? (1 - blankSizeX + 'em') : ('⿵⿶⿴'.includes(structureType) ? ((1 - blankSizeX) / 2 + 'em') : 'unset'),
                    bottom: '⿸⿹⿵'.includes(structureType) ? (1 - blankSizeY + 'em') : ('⿷⿶⿼⿴'.includes(structureType) ? ((1 - blankSizeY) / 2 + 'em') : 'unset'),
                    // ⿰⿲⿱⿳⿸⿺⿹⿽⿵⿷⿶⿼⿴⿻
                }}
            />
        </div>
    );
}

function findAdjustment(character: string, structureType: string) {
    for (let i = 0; i < data.length; i++) {
        if (data[i][0] === character && data[i][1] === structureType) {
            return [data[i][2], data[i][3], data[i][4], data[i][5]];
        }
    }
    return [1, 1, 0, 0];
}

// character, structureType, scaleX, scaleY, blankSizeX, blankSizeY
const data: [string, string, number, number, number, number][] = [
    ['扌', '⿰', 2, 1, 0, 0]
];