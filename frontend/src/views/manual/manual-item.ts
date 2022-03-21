import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vaadin-form-layout/vaadin-form-layout.js';
import '@vaadin/vaadin-form-layout/vaadin-form-item.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/button/src/vaadin-button.js';

@customElement('manual-item')
export class ManualItem extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-form-layout>
 <vaadin-form-item>
  <label slot="label">Action</label>
  <vaadin-combo-box></vaadin-combo-box>
 </vaadin-form-item>
 <vaadin-form-item>
  <label slot="label">Enable</label>
  <vaadin-horizontal-layout theme="spacing-xl" style="align-items: center;">
   <vaadin-checkbox tabindex="" type="checkbox" value="on"></vaadin-checkbox>
   <vaadin-button>
     Delete 
   </vaadin-button>
  </vaadin-horizontal-layout>
 </vaadin-form-item>
</vaadin-form-layout>
`;
  }
}
