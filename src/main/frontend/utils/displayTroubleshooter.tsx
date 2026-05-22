import {useState} from "react";
import {Button, Dialog, RadioButton, RadioGroup} from "@vaadin/react-components";
import Cookie from "js-cookie";

const cjkMainCpRangeData: [number, number][] = [
    [  0x4e00,    0x9fff], // cjk
    [  0x3000,    0x303f], // symb & punct
    [  0xf900,    0xfaff], // comp
    [  0x2e80,    0x2eff]  // sup
];

const cjkExtCpRangeData: [string, [number, number]][] = [
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

// characters which were encoded into one of the previous cjk extensions at meantime
const cpPlaceException: [number, string][] = [
    [0x2b735, 'H'],
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
        window.dispatchEvent(new Event('cjkExtChanged'));
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
                <span><h5>CJK Cơ bản</h5> <p>{String.fromCodePoint(cjkMainCpRangeData[0][0], cjkMainCpRangeData[0][0] + 1, cjkMainCpRangeData[0][0] + 2)}</p></span>
                {cjkExtCpRangeData.map((ext) => (
                    <span><h5>CJK Ext{ext[0]}</h5> <p>{String.fromCodePoint(ext[1][0], ext[1][0] + 1, ext[1][0] + 2)}</p></span>
                ))}
            </div>
            <RadioGroup
                label={'Chọn phần mở rộng chữ Hán cao nhất bạn có thể thấy:'}
                value={cjkExt}
                onValueChanged={(e) => setCjkExt(e.detail.value ?? '???')}
                style={{ display: 'flex', flexWrap: 'wrap', gap: 'var(--lumo-space-m)' }}
            >
                {['0', ...cjkExtCpRangeData.map(ext => ext[0]).flat()].map(ext => (
                    <RadioButton
                        key={ext} value={ext} label={ext}
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
    let hasCpPlaceException = false;
    let isInCjkMainCpRange = false;

    // if the char is one of the exception of cjk extension place
    for (let i = 0; i < cpPlaceException.length; i++) {
        if (cpPlaceException[i][0] == codepoint) {
            realityCjkExt = cpPlaceException[i][1];
            hasCpPlaceException = true;
            break;
        }
    }
    if (!hasCpPlaceException) {
        // if the char is in base codepoint ranges
        for (let i = 0; i < cjkMainCpRangeData.length; i++) {
            if (cjkMainCpRangeData[i][0] <= codepoint && codepoint <= cjkMainCpRangeData[i][1]) {
                realityCjkExt = "0";
                isInCjkMainCpRange = true;
                break;
            }
        }
        if (!isInCjkMainCpRange) {
            for (let i = 0; i < cjkExtCpRangeData.length; i++) {
                if (cjkExtCpRangeData[i][1][0] <= codepoint && codepoint <= cjkExtCpRangeData[i][1][1]) {
                    realityCjkExt = cjkExtCpRangeData[i][0];
                    break;
                }
            }
        }
    }
    let cjkExtName = cjkExtCpRangeData.map(x => x[0]);
    return cjkExtName.indexOf(realityCjkExt) <= cjkExtName.indexOf(selectedCjkExt);

}