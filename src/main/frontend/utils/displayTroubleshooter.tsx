import {useState} from "react";
import {Button, Dialog, RadioButton, RadioGroup} from "@vaadin/react-components";
import Cookie from "js-cookie";

const cjkData: [string, [number, number]][] = [
    ['0', [  0x4e00,     0x9fff]],
    ['A', [  0x3400,     0x4dbf]],
    ['B', [ 0x20000,    0x2a6df]],
    ['C', [ 0x2a700,    0x2b73f]],
    ['D', [ 0x2b740,    0x2b81f]],
    ['E', [ 0x2b820,    0x2ceaf]],
    ['F', [ 0x2ceb0,    0x2ebef]],
    ['G', [ 0x30000,    0x3134f]],
    ['H', [ 0x31350,    0x323af]],
    ['I', [ 0x2ebf0,    0x2ee5f]],
    ['J', [ 0x323b0,    0x3347f]]
];

export {
    inSupportedCjkRange,
    DisplayTroubleshooter
};
const DisplayTroubleshooter = ({ onClose }: { onClose?: () => void }) => {
    const [cjkExt, setCjkExt] = useState<string>(() => {
        return Cookie.get('cjkExt') ?? 'J';
    });
    const applyCjkExt = () => {
        Cookie.set('cjkExt', cjkExt, {expires: 365});
        if (onClose) onClose();
        window.location.reload();
    }

    return (
        <Dialog
            opened={true}
            onOpenedChanged={(e) => {
                if (!e.detail.value && onClose) {
                    onClose();
                }
            }}
        >
            <div
                style={{
                    display: 'grid',
                    gridTemplateColumns: '1fr 1fr 1fr 1fr',
                    gap: 'var(--lumo-space-m)',
                    alignItems: 'start',
                }}
            >
                <span><h5>CJK Ext0</h5> <p>{String.fromCodePoint(cjkData[0][1][0], cjkData[0][1][0] + 1, cjkData[0][1][0] + 2)}</p></span>
                <span><h5>CJK ExtA</h5> <p>{String.fromCodePoint(cjkData[1][1][0], cjkData[1][1][0] + 1, cjkData[1][1][0] + 2)}</p></span>
                <span><h5>CJK ExtB</h5> <p>{String.fromCodePoint(cjkData[2][1][0], cjkData[2][1][0] + 1, cjkData[2][1][0] + 2)}</p></span>
                <span><h5>CJK ExtC</h5> <p>{String.fromCodePoint(cjkData[3][1][0], cjkData[3][1][0] + 1, cjkData[3][1][0] + 2)}</p></span>
                <span><h5>CJK ExtD</h5> <p>{String.fromCodePoint(cjkData[4][1][0], cjkData[4][1][0] + 1, cjkData[4][1][0] + 2)}</p></span>
                <span><h5>CJK ExtE</h5> <p>{String.fromCodePoint(cjkData[5][1][0], cjkData[5][1][0] + 1, cjkData[5][1][0] + 2)}</p></span>
                <span><h5>CJK ExtF</h5> <p>{String.fromCodePoint(cjkData[6][1][0], cjkData[6][1][0] + 1, cjkData[6][1][0] + 2)}</p></span>
                <span><h5>CJK ExtG</h5> <p>{String.fromCodePoint(cjkData[7][1][0], cjkData[7][1][0] + 1, cjkData[7][1][0] + 2)}</p></span>
                <span><h5>CJK ExtH</h5> <p>{String.fromCodePoint(cjkData[8][1][0], cjkData[8][1][0] + 1, cjkData[8][1][0] + 2)}</p></span>
                <span><h5>CJK ExtI</h5> <p>{String.fromCodePoint(cjkData[9][1][0], cjkData[9][1][0] + 1, cjkData[9][1][0] + 2)}</p></span>
                <span><h5>CJK ExtJ</h5> <p>{String.fromCodePoint(cjkData[10][1][0],cjkData[10][1][0] + 1,cjkData[10][1][0] + 2)}</p></span>
            </div>
            <RadioGroup
                label={'Chọn phần mở rộng chữ Hán cao nhất bạn có thể thấy:'}
                value={cjkExt}
                onValueChanged={(e) => setCjkExt(e.detail.value ?? '???')}
                style={{ display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)' }}
            >
                {['0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'].map(ext => (
                    <RadioButton
                        key={ext} value={ext} label={'Ext ' + ext}
                    />
                ))}
            </RadioGroup>
            <Button theme={'primary'} onClick={applyCjkExt}>Lưu lựa chọn này</Button>
        </Dialog>
    );
}

function inSupportedCjkRange(codepoint: number) {
    let selectedCjkExt = Cookie.get('cjkExt') ?? 'J';
    let realityCjkExt = 'J';
    for (let i = 0; i < cjkData.length; i++) {
        if (cjkData[i][1][0] <= codepoint && codepoint <= cjkData[i][1][1]) {
            realityCjkExt = cjkData[i][0];
            break;
        }
    }
    let cjkExtName = cjkData.map(x => x[0]);
    return cjkExtName.indexOf(realityCjkExt) <= cjkExtName.indexOf(selectedCjkExt);

}